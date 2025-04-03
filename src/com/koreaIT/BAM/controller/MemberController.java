package com.koreaIT.BAM.controller;

import java.util.Scanner;

import com.koreaIT.BAM.service.MemberService;
import com.koreaIT.BAM.util.Util;

public class MemberController {

	private MemberService memberService;
	private Scanner sc;
	
	public MemberController(Scanner sc) {
		this.memberService = new MemberService();
		this.sc = sc;
	}
	
	public void doJoin() {
		String loginId = null;
		String loginPw = null;
		String name = null;
		
		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();
			
			if (loginId.length() == 0) {
				System.out.println("아이디는 필수 입력 정보입니다");
				continue;
			}
			
			boolean isLoginIdDup = this.memberService.isLoginIdDup(loginId);
			
			if (isLoginIdDup) {
				System.out.printf("[ %s ]은(는) 이미 사용중인 아이디 입니다\n", loginId);
				continue;
			}
			
			System.out.printf("[ %s ]은(는) 사용가능한 아이디 입니다\n", loginId);
			break;
		}
		
		while(true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			
			if (loginPw.length() == 0) {
				System.out.println("비밀번호는 필수 입력 정보입니다");
				continue;
			}
			
			System.out.printf("비밀번호 확인 : ");
			String loginPwChk = sc.nextLine();
		
			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호가 일치하지 않습니다");
				continue;
			}
			
			break;
		}
		
		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine();
			
			if (name.length() == 0) {
				System.out.println("이름은 필수 입력 정보입니다");
				continue;
			}
			
			break;
		}
		
		int id = this.memberService.joinMember(Util.getDateStr(), loginId, loginPw, name);
		
		System.out.printf("%d번 회원이 가입되었습니다.\n", id);
	}

}
