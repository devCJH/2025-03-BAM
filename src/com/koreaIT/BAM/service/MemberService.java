package com.koreaIT.BAM.service;

import com.koreaIT.BAM.dao.MemberDao;
import com.koreaIT.BAM.dto.Member;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = new MemberDao();
	}
	
	public boolean isLoginIdDup(String loginId) {
		return memberDao.isLoginIdDup(loginId);
	}

	public int joinMember(String regDate, String loginId, String loginPw, String name) {
		return memberDao.joinMember(regDate, loginId, loginPw, name);
	}

//	public Member getMemberByLoginIdAndPw(String loginId, String loginPw) {
//		return memberDao.getMemberByLoginIdAndPw(loginId, loginPw);
//	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

}
