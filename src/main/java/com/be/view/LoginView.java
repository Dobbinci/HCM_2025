package com.be.view;

import com.be.controller.MemberController;
import com.be.model.Professor;
import com.be.model.Staff;
import com.be.model.Member;
import com.be.model.Student;
import jakarta.persistence.EntityManager;

public class LoginView extends TemplateLoginView {
    private MemberController memberController;

    public LoginView(EntityManager em) {
        this.memberController = new MemberController(em);
    }

    @Override
    protected Member login() {
        Member loggedInMember = null;

        while (loggedInMember == null) {
            System.out.println("아이디와 비밀번호를 입력하시오!");
            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("PW: ");
            String pw = scanner.nextLine();

            loggedInMember = memberController.login(id, pw);
        }

        if (loggedInMember instanceof Professor professor) {
            System.out.println("로그인 성공! 환영합니다, " + professor.getName() + " 교수님!");
        } else if (loggedInMember instanceof Student student) {
            System.out.println("로그인 성공! 환영합니다, " + student.getName() + " 학생님!");
        } else if (loggedInMember instanceof Staff staff) {
            System.out.println("로그인 성공! 환영합니다, " + staff.getName() + " 직원님!");
        }

        return loggedInMember;
    }

    protected void signup() {
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

