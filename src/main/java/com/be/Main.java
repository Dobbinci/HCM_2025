package com.be;

import com.be.service.Professor;
import com.be.service.Staff;
import com.be.service.Student;
import com.be.view.LoginView;
import com.be.view.professor.ProfCourseApplicationViewStrategy;
import com.be.view.professor.ProfessorHomeView;
import com.be.view.staff.StaffHomeView;
import com.be.service.Member;
import com.be.view.TemplateLoginView;

public class Main {
    public static void main(String[] args) {

        TemplateLoginView loginView = new LoginView();
        Member loggedInMember = loginView.loginOrSignupFlow(); //로그인 시도
        
         if (loggedInMember instanceof Professor professor) {
             ProfessorHomeView professorHomeView = new ProfessorHomeView(new ProfCourseApplicationViewStrategy());
             professorHomeView.show(professor);
        } else if (loggedInMember instanceof Student) {
            Student student = (Student) loggedInMember;
            // TODO: StudentView studentView = new StudentView(student);
            // TODO: studentView.home();
            System.out.println("(아직 StudentView 없어서 출력만 합니다) " + student.getName() + " 학생님 메인 화면으로 이동합니다!");
        } else if (loggedInMember instanceof Staff staff) {
            StaffHomeView.show(staff);
        } else {
            System.out.println("알 수 없는 사용자 타입입니다.");
        }
    }
}