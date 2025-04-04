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

	public List<Article> getArticles() {
		return this.articles;
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
}













