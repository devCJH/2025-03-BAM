package com.koreaIT.BAM.service;

import java.util.List;

import com.koreaIT.BAM.dao.ArticleDao;
import com.koreaIT.BAM.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = new ArticleDao();
	}

	public int writeArticle(String regDate, String title, String content) {
		return this.articleDao.writeArticle(regDate, title, content);
	}

	public List<Article> getArticles() {
		return this.articleDao.getArticles();
	}

	public String getSearchKeywordByCmd(String cmd) {
		return cmd.substring(12).trim();
	}

	public List<Article> getPrintArticles(String searchKeyword) {
		return this.articleDao.getPrintArticles(searchKeyword);
	}

}
