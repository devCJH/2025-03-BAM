package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.dto.Member;
import com.koreaIT.BAM.util.Util;

public class App {
	private int lastArticleId;
	private List<Article> articles;
	private int lastMemberId;
	private List<Member> members;

	public App() {
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();
		this.lastMemberId = 0;
		this.members = new ArrayList<>();
	}

	public void run() {
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
			if (cmd.equals("member join")) {
				
				String loginId = null;
				String loginPw = null;
				String name = null;
				
				while (true) {
					System.out.printf("아이디 : ");
					loginId = sc.nextLine().trim();
					
					if (loginId.length() == 0) {
						System.out.println("아이디는 필수 입력 정보입니다");
						continue;
					}
					
					boolean isLoginIdDup = false;
					
					for (Member member : members) {
						if (member.getLoginId().equals(loginId)) {
							isLoginIdDup = true;
							break;
						}
					}
					
					if (isLoginIdDup) {
						System.out.printf("[ %s ]은(는) 이미 사용중인 아이디 입니다\n", loginId);
						continue;
					}
					
					System.out.printf("[ %s ]은(는) 사용가능한 아이디 입니다\n", loginId);
					break;
				}
				
				while(true) {
					System.out.printf("비밀번호 : ");
					loginPw = sc.nextLine().trim();
					
					if (loginPw.length() == 0) {
						System.out.println("비밀번호는 필수 입력 정보입니다");
						continue;
					}
					
					System.out.printf("비밀번호 확인 : ");
					String loginPwChk = sc.nextLine();
				
					if (loginPw.equals(loginPwChk) == false) {
						System.out.println("비밀번호가 일치하지 않습니다");
						continue;
					}
					
					break;
				}
				
				while (true) {
					System.out.printf("이름 : ");
					name = sc.nextLine();
					
					if (name.length() == 0) {
						System.out.println("이름은 필수 입력 정보입니다");
						continue;
					}
					
					break;
				}
				
				this.lastMemberId++;

				Member member = new Member(this.lastMemberId, Util.getDateStr(), loginId, loginPw, name);
				this.members.add(member);

				System.out.printf("%d번 회원이 가입되었습니다.\n", this.lastMemberId);
			} else if (cmd.equals("article write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String content = sc.nextLine();
				this.lastArticleId++;

				Article article = new Article(this.lastArticleId, Util.getDateStr(), title, content);

				this.articles.add(article);

				System.out.printf("%d번 게시글이 작성되었습니다\n", this.lastArticleId);
			} else if (cmd.startsWith("article list")) {

				if (this.articles.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다");
					continue;
				}

				String searchKeyword = cmd.substring(12).trim();

				List<Article> printArticles = this.articles;

				if (searchKeyword.length() > 0) {
					System.out.println("검색어 : " + searchKeyword);
					printArticles = new ArrayList<>();

					for (Article article : this.articles) {
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

				this.articles.remove(foundArticle);

				System.out.printf("%d번 게시글이 삭제되었습니다\n", foundArticle.getId());
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}

		System.out.println("== 프로그램 종료 ==");
		sc.close();
	}

	private int getNumByCmd(String cmd) {
		String[] cmdBits = cmd.split(" ");

		try {
			return Integer.parseInt(cmdBits[2]);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	private Article getArticleById(int id) {
		for (Article article : this.articles) {
			if (id == article.getId()) {
				return article;
			}
		}
		return null;
	}

	private void makeTestData() {
		System.out.println("테스트용 게시글 데이터 5개를 생성했습니다");
		for (int i = 1; i <= 5; i++) {
			this.articles.add(new Article(++this.lastArticleId, Util.getDateStr(), "제목" + i, "내용" + i));
		}
	}
}
