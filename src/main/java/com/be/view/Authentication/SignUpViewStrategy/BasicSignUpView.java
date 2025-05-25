package com.be.view.Authentication.SignUpViewStrategy;

import com.be.controller.MemberController;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class BasicSignUpView implements SignUpViewStrategy {
    private MemberController memberController;
    private Scanner scanner;
    public BasicSignUpView(EntityManager em, Scanner scanner) {

        this.memberController = new MemberController(em);
        this.scanner = scanner;
    }

    @Override
    public void signup() {
        Long id = 0L;
        String systemId = "", password = "", name = "", position = "";

        System.out.println("학번/직번을 입력하세요");
        id = Long.valueOf(scanner.nextLine());
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

        memberController.saveMember(id, name, systemId, password, position);
    }
}
