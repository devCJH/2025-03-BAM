package com.koreaIT.BAM.session;

public class Session {
	public static int loginedMemberId;
	
	static {
		loginedMemberId = -1;
	}
	
	public static boolean isLogined() {
		return loginedMemberId != -1;
	}
}
