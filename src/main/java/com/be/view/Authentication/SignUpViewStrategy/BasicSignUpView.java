package com.be.view.Authentication.SignUpViewStrategy;

import com.be.controller.MemberControllerFacade;

import java.util.Scanner;

public class BasicSignUpView implements SignUpViewStrategy {
    private MemberControllerFacade memberControllerFacade;

    public BasicSignUpView(MemberControllerFacade memberControllerFacade) {

        this.memberControllerFacade = memberControllerFacade;

    }

    @Override
    public void signup() {
        Scanner scanner = new Scanner(System.in);
        String systemId = "", memberId = "", password = "", name = "", position = "", hanmadi = "";

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
        System.out.println("ID를 입력하세요: ");
        systemId = scanner.nextLine();
        System.out.println("비밀번호를 입력하세요: ");
        password = scanner.nextLine();
        if(!position.equals("staff")) {
            System.out.println("각오를 입력하세요: ");
        }else{
            System.out.println("연락처를 입력하세요(문의용): ");
        }
        hanmadi = scanner.nextLine();


        memberControllerFacade.saveMember(memberId, name, systemId, password, position, hanmadi, false);
    }
}
