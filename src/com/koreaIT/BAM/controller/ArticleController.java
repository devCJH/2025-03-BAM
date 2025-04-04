package com.koreaIT.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.service.ArticleService;
import com.koreaIT.BAM.util.Util;

public class ArticleController {

	private ArticleService articleService;
	private Scanner sc;
	
	public ArticleController(Scanner sc) {
		this.articleService = new ArticleService();
		this.sc = sc;
	}

	public void doWrite() {
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String content = sc.nextLine();
		
		int id = this.articleService.writeArticle(Util.getDateStr(), title, content);

		System.out.printf("%d번 게시글이 작성되었습니다\n", id);		
	}

	public void showList(String cmd) {
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
		
		System.out.println("번호	|	제목	|	작성일	");
		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);
			System.out.printf("%d	|	%s	|%s\n", article.getId(), article.getTitle(), article.getRegDate());
		}
	}
}










