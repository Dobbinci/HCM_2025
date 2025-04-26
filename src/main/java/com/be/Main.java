package com.be;

import com.be.control.CourseManager;
import com.be.control.MemberManager;
import com.be.service.Professor;
import com.be.view.LoginView;
import com.be.view.professor.ProfessorView;


public class Main {
    public static void main(String[] args) {
        CourseManager courseManager = new CourseManager();
        MemberManager memberManager = new MemberManager();

        // membermanager에서 아이디와 비번이 일치하는 Professor 객체를 가져온다.

        // Login 수행
        LoginView loginView = new LoginView();
        
        memberManager.initDummyData();
       
        // Login 성공 후 교수 홈 화면으로 이동
        ProfessorView professorView = new ProfessorView(loginView.login());
        professorView.home();
    }
}