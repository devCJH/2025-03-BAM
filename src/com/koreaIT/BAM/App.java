package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.controller.ArticleController;
import com.koreaIT.BAM.controller.MemberController;
import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.util.Util;

public class App {
	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

//		makeTestData();

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			
			if (cmd.equals("member join")) {
				memberController.doJoin();
			} else if (cmd.equals("article write")) {
				articleController.doWrite();
			} else if (cmd.startsWith("article list")) {
				
				articleController.showList(cmd);
				
			} else if (cmd.startsWith("article detail ")) {
				int id = getNumByCmd(cmd);

				if (id == -1) {
					System.out.println("게시글 번호를 정확하게 입력해주세요");
					continue;
				}

//				Article foundArticle = getArticleById(id);
//
//				if (foundArticle == null) {
//					System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
//					continue;
//				}
//
//				System.out.printf("== %d번 게시글 상세보기 ==\n", foundArticle.getId());
//				System.out.printf("번호 : %d\n", foundArticle.getId());
//				System.out.printf("작성일 : %s\n", foundArticle.getRegDate());
//				System.out.printf("제목 : %s\n", foundArticle.getTitle());
//				System.out.printf("내용 : %s\n", foundArticle.getContent());
//			} 
//			else if (cmd.startsWith("article modify ")) {
//				int id = getNumByCmd(cmd);
//
//				if (id == -1) {
//					System.out.println("게시글 번호를 정확하게 입력해주세요");
//					continue;
//				}
//
//				Article foundArticle = getArticleById(id);
//
//				if (foundArticle == null) {
//					System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
//					continue;
//				}
//
//				System.out.printf("수정할 제목 : ");
//				String title = sc.nextLine();
//				System.out.printf("수정할 내용 : ");
//				String content = sc.nextLine();
//
//				foundArticle.setTitle(title);
//				foundArticle.setContent(content);
//
//				System.out.printf("%d번 게시글이 수정되었습니다\n", foundArticle.getId());
//			} 
//			else if (cmd.startsWith("article delete ")) {
//				int id = getNumByCmd(cmd);
//
//				if (id == -1) {
//					System.out.println("게시글 번호를 정확하게 입력해주세요");
//					continue;
//				}
//
//				Article foundArticle = getArticleById(id);
//
//				if (foundArticle == null) {
//					System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
//					continue;
//				}
//
//				this.articles.remove(foundArticle);
//
//				System.out.printf("%d번 게시글이 삭제되었습니다\n", foundArticle.getId());
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}

		System.out.println("== 프로그램 종료 ==");
		sc.close();
	}

	private int getNumByCmd(String cmd) {
		String[] cmdBits = cmd.split(" ");

		try {
			return Integer.parseInt(cmdBits[2]);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

//	private Article getArticleById(int id) {
//		for (Article article : this.articles) {
//			if (id == article.getId()) {
//				return article;
//			}
//		}
//		return null;
//	}
//
//	private void makeTestData() {
//		System.out.println("테스트용 게시글 데이터 5개를 생성했습니다");
//		for (int i = 1; i <= 5; i++) {
//			this.articles.add(new Article(++this.lastArticleId, Util.getDateStr(), "제목" + i, "내용" + i));
//		}
//	}
}
