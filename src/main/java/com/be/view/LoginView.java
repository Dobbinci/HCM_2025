package com.be.view;

import com.be.control.MemberManager;
import com.be.service.Professor;
import com.be.model.Staff;
import com.be.service.Student;
import com.be.model.Member;

public class LoginView extends TemplateLoginView {
    MemberManager manager = MemberManager.getInstance();
    @Override
    protected Member login() {
        Member loggedInMember = null;

        while (loggedInMember == null) {
            System.out.println("아이디와 비밀번호를 입력하시오!");
            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("PW: ");
            String pw = scanner.nextLine();

            loggedInMember = manager.manageLogin(id, pw);

            if (loggedInMember == null) {
                System.out.println("로그인 실패! 아이디 또는 비밀번호가 올바르지 않습니다.\n다시 입력하세요.");
            }
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

    @Override
    protected void signup() {
        String id = "", password = "", name = "", userid = "", position = "";

        System.out.println("학번/직번을 입력하세요");
        userid = scanner.nextLine();
        System.out.println("신분을 입력하세요\n1.professor\n2.student\n3.staff");
        position = scanner.nextLine();
        if (position.equals("1")) {
            position = "professor";
        } else if (position.equals("2")) {
            position = "student";
        } else if (position.equals("3")) {
            position = "staff";
        }
        else{
            System.out.println("잘못된 신분입니다.");
        }

        if (position.equals("student") || position.equals("professor") || position.equals("staff")) {
            if (manager.checkMemberRegistration(userid, position)) {
                System.out.println("이미 가입이 되어있습니다");
            } else {
                boolean sameidCheck = true;
                while (sameidCheck) {
                    System.out.println("이름을 입력하세요");
                    name = scanner.nextLine();
                    System.out.println("ID를 입력하세요: ");
                    id = scanner.nextLine();
                    System.out.println("비밀번호를 입력하세요: ");
                    password = scanner.nextLine();
                    sameidCheck = manager.sameIdCheck(id);
                }
                manager.saveMember(id, password, name, userid, position);
                System.out.println(name + "님의 가입을 환영합니다.");
            }
        }
    }
}
