package com.koreaIT.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.service.ArticleService;
import com.koreaIT.BAM.session.Session;
import com.koreaIT.BAM.util.Util;

public class ArticleController extends Controller {

	private ArticleService articleService;
	
	public ArticleController(Scanner sc) {
		this.articleService = new ArticleService();
		this.sc = sc;
	}

	@Override
	public void doAction(String methodName, String cmd) {
		this.cmd = cmd;
		
		switch (methodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
			break;
		}
	}

	public void doWrite() {
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String content = sc.nextLine();
		
		int id = this.articleService.writeArticle(Util.getDateStr(), Session.loginedMemberId, title, content);

		System.out.printf("%d번 게시글이 작성되었습니다\n", id);		
	}

	public void showList() {
		String searchKeyword = this.articleService.getSearchKeywordByCmd(cmd);
		List<Article> printArticles = this.articleService.getPrintArticles(searchKeyword);

		
		if (searchKeyword.length() > 0) {
			System.out.println("검색어 : " + searchKeyword);
			
			if (printArticles == null) {
				System.out.println("검색결과가 없습니다");
				return;
			}	
		}
		
		if (printArticles == null) {
			System.out.println("게시글이 존재하지 않습니다");
			return;
		}
		
		System.out.println("번호	|	제목	|	작성자	|	작성일	");
		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);
			
			String writerName = articleService.getWriterNameByMemberId(article.getMemberId());
			
			System.out.printf("%d	|	%s	|	%s	|%s\n", article.getId(), article.getTitle(), writerName, article.getRegDate());
		}
	}

	public void showDetail() {
		int id = this.articleService.getNumByCmd(cmd);

		if (id == -1) {
			System.out.println("게시글 번호를 정확하게 입력해주세요");
			return;
		}

		Article foundArticle = this.articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
			return;
		}

		String writerName = articleService.getWriterNameByMemberId(foundArticle.getMemberId());
		
		System.out.printf("== %d번 게시글 상세보기 ==\n", foundArticle.getId());
		System.out.printf("번호 : %d\n", foundArticle.getId());
		System.out.printf("작성일 : %s\n", foundArticle.getRegDate());
		System.out.printf("작성자 : %s\n", writerName);
		System.out.printf("제목 : %s\n", foundArticle.getTitle());
		System.out.printf("내용 : %s\n", foundArticle.getContent());
	}

	public void doModify() {
		int id = this.articleService.getNumByCmd(cmd);

		if (id == -1) {
			System.out.println("게시글 번호를 정확하게 입력해주세요");
			return;
		}

		Article foundArticle = this.articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
			return;
		}

		if (Session.loginedMemberId != foundArticle.getMemberId()) {
			System.out.println("해당 게시물에 대한 권한이 없습니다");
			return;
		}
		
		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String content = sc.nextLine();

		this.articleService.modifyArticle(foundArticle, title, content);

		System.out.printf("%d번 게시글이 수정되었습니다\n", foundArticle.getId());
	}

	public void doDelete() {
		int id = this.articleService.getNumByCmd(cmd);

		if (id == -1) {
			System.out.println("게시글 번호를 정확하게 입력해주세요");
			return;
		}

		Article foundArticle = this.articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
			return;
		}

		if (Session.loginedMemberId != foundArticle.getMemberId()) {
			System.out.println("해당 게시물에 대한 권한이 없습니다");
			return;
		}
		
		this.articleService.deleteArticle(foundArticle);

		System.out.printf("%d번 게시글이 삭제되었습니다\n", foundArticle.getId());
	}

	@Override
	public void makeTestData() {
		System.out.println("테스트용 게시글 데이터 5개를 생성했습니다");
		this.articleService.makeTestData();
	}
}
