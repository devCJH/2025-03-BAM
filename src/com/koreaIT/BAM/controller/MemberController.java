package com.koreaIT.BAM.controller;

import java.util.Scanner;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.dto.Member;
import com.koreaIT.BAM.service.MemberService;
import com.koreaIT.BAM.util.Util;

public class MemberController {

	private MemberService memberService;
	private Scanner sc;
	public static int loginedMemberId;
	
	public MemberController(Scanner sc) {
		this.memberService = Container.memberService;
		this.sc = sc;
		loginedMemberId = -1;
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

	public void doLogin() {
		if (loginedMemberId != -1) {
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}
		
		System.out.printf("아이디 : ");
		String loginId = sc.nextLine().trim();
		System.out.printf("비밀번호 : ");
		String loginPw = sc.nextLine().trim();
		
		Member member = this.memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			System.out.printf("[ %s ]은(는) 존재하지 않는 아이디입니다\n", loginId);
			return;
		}
		
		if (member.getLoginPw().equals(loginPw) == false) {
			System.out.println("비밀번호가 일치하지 않습니다");
			return;
		}
		
		loginedMemberId = member.getId();
		
		System.out.printf("[ %s ] 님 환영합니다~\n", member.getName());
	}
	
//	public void doLogin() {
//		if (this.loginedMemberId != -1) {
//			System.out.println("로그아웃 후 이용해주세요");
//			return;
//		}
//		
//		System.out.printf("아이디 : ");
//		String loginId = sc.nextLine().trim();
//		System.out.printf("비밀번호 : ");
//		String loginPw = sc.nextLine().trim();
//		
//		Member member = this.memberService.getMemberByLoginIdAndPw(loginId, loginPw);
//		
//		if (member == null) {
//			System.out.println("아이디 or 비밀번호가 잘못되었습니다");
//			return;
//		}
//		
//		this.loginedMemberId = member.getId();
//		
//		System.out.printf("[ %s ] 님 환영합니다~\n", member.getName());
//	}

	public void doLogout() {
		if (loginedMemberId == -1) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		loginedMemberId = -1;
		System.out.println("정상적으로 로그아웃 되었습니다");
	}

	public void makeTestData() {
		System.out.println("테스트용 회원 데이터 3개를 생성했습니다");
		this.memberService.makeTestData();
	}

}


















