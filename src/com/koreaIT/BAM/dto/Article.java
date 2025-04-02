package com.koreaIT.BAM.dto;

public class Article {
	private int id;
	private String regDate;
	private String title;
	private String content;

	public Article(int id, String regDate, String title, String content) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
