package com.koreaIT.BAM.dao;

import java.util.List;

import com.koreaIT.BAM.db.InsteadDB;
import com.koreaIT.BAM.dto.Member;
import com.koreaIT.BAM.util.Util;

public class MemberDao {
	
	private int lastMemberId;
	private List<Member> members;
	
	public MemberDao() {
		this.lastMemberId = InsteadDB.lastMemberId;
		this.members = InsteadDB.members;
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

	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.getLoginId().equals(loginId)) {
				return member;
			}
		}
		return null;
	}
	
	public void makeTestData() {
		for (int i = 1; i <= 3; i++) {
			this.members.add(new Member(++this.lastMemberId, Util.getDateStr(), "test" + i, "test" + i, "유저" + i));
		}
	}
}







