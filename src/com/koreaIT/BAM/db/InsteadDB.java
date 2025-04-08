package com.koreaIT.BAM.db;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.dto.Member;

public class InsteadDB {
	public static int lastMemberId;
	public static int lastArticleId;
	public static List<Member> members;
	public static List<Article> articles;
	
	static {
		lastMemberId = 0;
		lastArticleId = 0;
		members = new ArrayList<>();
		articles = new ArrayList<>();
	}
}
