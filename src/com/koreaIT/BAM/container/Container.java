package com.koreaIT.BAM.container;

import com.koreaIT.BAM.dao.ArticleDao;
import com.koreaIT.BAM.dao.MemberDao;
import com.koreaIT.BAM.service.ArticleService;
import com.koreaIT.BAM.service.MemberService;

public class Container {
	public static MemberDao memberDao;
	public static MemberService memberService;
	public static ArticleDao articleDao;
	public static ArticleService articleService;
	
	static {
		memberDao = new MemberDao();
		memberService = new MemberService();
		articleDao = new ArticleDao();
		articleService = new ArticleService();
	}
}
