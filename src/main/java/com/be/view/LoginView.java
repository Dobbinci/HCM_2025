package com.be.view;

import java.util.Scanner;
import com.be.control.MemberManager;
import com.be.service.Professor;

public class LoginView {

    private MemberManager memberManager; // 필드로 선언

    // 생성자로 memberManager 받아오기
    public LoginView(MemberManager memberManager) {
        this.memberManager = memberManager;
    }

    public Professor login() {
        Scanner scanner = new Scanner(System.in);
        Professor loggedInProfessor = null;
       
        while (loggedInProfessor == null) { // 로그인 성공할 때까지 반복
            System.out.println("아이디와 비밀번호를 입력하시오!");
            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("PW: ");
            String pw = scanner.nextLine();

            loggedInProfessor = memberManager.manageLogin(id, pw);

            if (loggedInProfessor == null) {
                System.out.println("로그인 실패! 아이디 또는 비밀번호가 올바르지 않습니다.\n다시 입력하세요.");
            }
        }

        System.out.println("로그인 성공! 환영합니다, " + loggedInProfessor.getName() + " 교수님!");
        return loggedInProfessor;
    }
}
