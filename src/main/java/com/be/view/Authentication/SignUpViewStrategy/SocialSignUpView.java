package com.be.view.Authentication.SignUpViewStrategy;

import com.be.controller.MemberControllerFacade;

import java.util.Scanner;

public class SocialSignUpView implements SignUpViewStrategy {
    private MemberControllerFacade memberControllerFacade;

    public SocialSignUpView(MemberControllerFacade memberControllerFacade) {

        this.memberControllerFacade = memberControllerFacade;
    }

    @Override
    public void signup() {
        Scanner scanner = new Scanner(System.in);
        String memberId = "", socialId="", socialPw = "", name = "", position = "";

        System.out.println("학번/직번을 입력하세요");
        memberId = scanner.nextLine();
        System.out.println("신분을 입력하세요\n1.professor\n2.student\n3.staff");
        position = scanner.nextLine();
        switch (position) {
            case "1" -> position = "professor";
            case "2" -> position = "student";
            case "3" -> position = "staff";
            default -> {
                System.out.println("잘못된 신분입니다.");
                return;
            }
        }


        System.out.println("이름을 입력하세요");
        name = scanner.nextLine();
        System.out.println("소셜 ID(email 형식: example@naver.com)를 입력하세요: ");
        socialId = scanner.nextLine();
        System.out.println("소셜 비밀번호를 입력하세요: ");
        socialPw = scanner.nextLine();

        memberControllerFacade.saveMember(memberId, name, socialId, socialPw, position, true);
    }
}
