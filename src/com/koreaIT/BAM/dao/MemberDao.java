package com.koreaIT.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.BAM.dto.Member;

public class MemberDao {

	private int lastMemberId;
	private List<Member> members;
	
	public MemberDao() {
		this.lastMemberId = 0;
		this.members = new ArrayList<>();
	}
	
	public boolean isLoginIdDup(String loginId) {
		for (Member member : members) {
			if (member.getLoginId().equals(loginId)) {
				return true;
			}
		}
		return false;
	}

	public int joinMember(String regDate, String loginId, String loginPw, String name) {
		Member member = new Member(++this.lastMemberId, regDate, loginId, loginPw, name);
		this.members.add(member);
		
		return this.lastMemberId;
	}

//	public Member getMemberByLoginIdAndPw(String loginId, String loginPw) {
//		for (Member member : members) {
//			if (member.getLoginId().equals(loginId) && member.getLoginPw().equals(loginPw)) {
//				return member;
//			}
//		}
//		return null;
//	}

	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.getLoginId().equals(loginId)) {
				return member;
			}
		}
		return null;
	}
}













