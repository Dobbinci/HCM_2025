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

        // 더미 데이터 먼저 생성
        memberManager.initDummyData();

        
        LoginView loginView = new LoginView(memberManager);
        Professor loggedInProfessor = loginView.login(); //로그인 시도
        
        ProfessorView professorView = new ProfessorView(loggedInProfessor);
        professorView.home();
    }
}
