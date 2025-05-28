package com.be.view.Authentication.LoginViewStrategy;

import com.be.controller.MemberControllerFacade;
import com.be.model.Member;
import com.be.model.Professor;
import com.be.model.Staff;
import com.be.model.Student;

import java.util.Scanner;

public class SocialLoginView implements LoginViewStrategy{
    private MemberControllerFacade memberControllerFacade;


    public SocialLoginView(MemberControllerFacade memberControllerFacade) {
        this.memberControllerFacade = memberControllerFacade;
    }

    @Override
    public Member login() {
        Scanner scanner = new Scanner(System.in);
        Member loggedInMember = null;

        while (loggedInMember == null) {
            System.out.println("소셜 아이디와 비밀번호를 입력하시오!");
            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("PW: ");
            String pw = scanner.nextLine();

            loggedInMember = memberControllerFacade.login(id, pw);
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
}
