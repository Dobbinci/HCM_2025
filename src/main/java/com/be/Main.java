package com.be;

import com.be.control.CourseManager;
import com.be.control.MemberManager;
import com.be.service.Professor;
import com.be.view.professor.ProfessorView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        CourseManager courseManager = new CourseManager();

        // membermanager에서 아이디와 비번이 일치하는 Professor 객체를 가져온다.
        Professor professor = new Professor();

        // Login 수행
        // Login 성공 후 교수 홈 화면으로 이동
        ProfessorView professorView = new ProfessorView(professor);
        professorView.home();
        //TIP Use <shortcut actionId="EditorChooseLookupItem"/> to choose an action.
    }
}