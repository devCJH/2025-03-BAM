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

	public String getSearchKeywordByCmd(String cmd) {
		return cmd.substring(12).trim();
	}

	public List<Article> getPrintArticles(String searchKeyword) {
		return this.articleDao.getPrintArticles(searchKeyword);
	}
	
	public int getNumByCmd(String cmd) {
		String[] cmdBits = cmd.split(" ");

		try {
			return Integer.parseInt(cmdBits[2]);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	public Article getArticleById(int id) {
		return this.articleDao.getArticleById(id);
	}

	public void modifyArticle(Article foundArticle, String title, String content) {
		this.articleDao.modifyArticle(foundArticle, title, content);
	}

	public void deleteArticle(Article foundArticle) {
		this.articleDao.deleteArticle(foundArticle);
	}

	public void makeTestData() {
		this.articleDao.makeTestData();
	}
}
