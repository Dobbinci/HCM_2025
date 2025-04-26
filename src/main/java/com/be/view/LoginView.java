package com.be.view;

import java.lang.reflect.Member;
import java.util.*;

import com.be.control.MemberManager;
import com.be.service.Professor;

public class LoginView {

    public Professor login(){
        Scanner scanner = new Scanner(System.in);
        Professor loggedInProfessor = null;
        String id;
        String pw;

        System.out.println("아이디와 비밀번호를 입력하시오!");
        System.out.print("ID: ");
        id = scanner.nextLine();
        System.out.print("PW: ");
        pw = scanner.nextLine();
        
        MemberManager memberManager = new MemberManager();
        loggedInProfessor = memberManager.manageLogin(id, pw);

        return loggedInProfessor;
    }
}
