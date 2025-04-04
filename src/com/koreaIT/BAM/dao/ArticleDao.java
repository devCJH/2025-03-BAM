package com.koreaIT.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.util.Util;

public class ArticleDao {

	private int lastArticleId;
	private List<Article> articles;
	
	public ArticleDao() {
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();
	}

	public int writeArticle(String regDate, String title, String content) {
		Article article = new Article(++this.lastArticleId, Util.getDateStr(), title, content);
		this.articles.add(article);
		return this.lastArticleId;
	}

	public List<Article> getPrintArticles(String searchKeyword) {
		List<Article> printArticles = this.articles;

		if (searchKeyword.length() > 0) {
			
			printArticles = new ArrayList<>();
			
			for (Article article : this.articles) {
				if (article.getTitle().contains(searchKeyword)) {
					printArticles.add(article);
				}
			}
			
			if (printArticles.size() == 0) {
				return null;
			}
		}
		
		if (this.articles.size() == 0) {
			return null;
		}
		
		return printArticles;
	}

	public Article getArticleById(int id) {
		for (Article article : this.articles) {
			if (id == article.getId()) {
				return article;
			}
		}
		return null;
	}

	public void modifyArticle(Article foundArticle, String title, String content) {
		foundArticle.setTitle(title);
		foundArticle.setContent(content);
	}

	public void deleteArticle(Article foundArticle) {
		this.articles.remove(foundArticle);
	}

	public void makeTestData() {
		for (int i = 1; i <= 5; i++) {
			this.articles.add(new Article(++this.lastArticleId, Util.getDateStr(), "제목" + i, "내용" + i));
		}
	}
}
