import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
	
	private static int lastArticleId;
	private static List<Article> articles;
	
	static {
		lastArticleId = 0;
		articles = new ArrayList<>();
	}
	
	public static void main(String[] args) {

		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		makeTestData();
		
		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.equals("exit")) {
				break;
			}
			
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			
			if (cmd.equals("article write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String content = sc.nextLine();
				lastArticleId++;
				
				Article article = new Article(lastArticleId, Util.getDateStr(), title, content);
				
				articles.add(article);
				
				System.out.printf("%d번 게시글이 작성되었습니다\n", lastArticleId);
			} else if (cmd.startsWith("article list")) {
				
				if (articles.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다");
					continue;
				}
				
				String searchKeyword = cmd.substring(12).trim();

				List<Article> printArticles = articles;
				
				if (searchKeyword.length() > 0) {
					System.out.println("검색어 : " + searchKeyword);
					printArticles = new ArrayList<>();
					
					for (Article article : articles) {
						if (article.getTitle().contains(searchKeyword)) {
							printArticles.add(article);
						}
					}
					
					if (printArticles.size() == 0) {
						System.out.println("검색결과가 없습니다");
						continue;
					}
				}
				
				System.out.println("번호	|	제목	|	작성일	");
				for (int i = printArticles.size() - 1; i >= 0; i--) {
					Article article = printArticles.get(i);
					System.out.printf("%d	|	%s	|%s\n", article.getId(), article.getTitle(), article.getRegDate());
				}
			} else if (cmd.startsWith("article detail ")) {
				int id = getNumByCmd(cmd);
				
				if (id == -1) {
					System.out.println("게시글 번호를 정확하게 입력해주세요");
					continue;
				}
				
				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
					continue;
				}
				
				System.out.printf("== %d번 게시글 상세보기 ==\n", foundArticle.getId());
				System.out.printf("번호 : %d\n", foundArticle.getId());
				System.out.printf("작성일 : %s\n", foundArticle.getRegDate());
				System.out.printf("제목 : %s\n", foundArticle.getTitle());
				System.out.printf("내용 : %s\n", foundArticle.getContent());
			} else if (cmd.startsWith("article modify ")) {
				int id = getNumByCmd(cmd);
				
				if (id == -1) {
					System.out.println("게시글 번호를 정확하게 입력해주세요");
					continue;
				}
				
				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
					continue;
				}
				
				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String content = sc.nextLine();
				
				foundArticle.setTitle(title);
				foundArticle.setContent(content);
				
				System.out.printf("%d번 게시글이 수정되었습니다\n", foundArticle.getId());
			} else if (cmd.startsWith("article delete ")) {
				int id = getNumByCmd(cmd);
				
				if (id == -1) {
					System.out.println("게시글 번호를 정확하게 입력해주세요");
					continue;
				}
				
				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
					continue;
				}
				
				articles.remove(foundArticle);
				
				System.out.printf("%d번 게시글이 삭제되었습니다\n", foundArticle.getId());
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}

		System.out.println("== 프로그램 종료 ==");
		sc.close();
	}

	private static int getNumByCmd(String cmd) {
		String[] cmdBits = cmd.split(" ");
		
		try {
			return Integer.parseInt(cmdBits[2]);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	private static Article getArticleById(int id) {
		for (Article article : articles) {
			if (id == article.getId()) {
				return article;
			}
		}
		return null;
	}

	private static void makeTestData() {
		System.out.println("테스트용 게시글 데이터 5개를 생성했습니다");
		for (int i = 1; i <= 5; i++) {
			articles.add(new Article(++lastArticleId, Util.getDateStr(), "제목" + i, "내용" + i));
		}
	}
}





