package com.be;

import com.be.control.CourseManager;
import com.be.control.MemberManager;
import com.be.service.Professor;
import com.be.service.Staff;
import com.be.service.Student;
import com.be.view.LoginView;
import com.be.view.professor.ProfessorView;
import com.be.service.Member;

public class Main {
    public static void main(String[] args) {
        CourseManager courseManager = new CourseManager();
        MemberManager memberManager = new MemberManager();

        // 더미 데이터 먼저 생성
        memberManager.initDummyData();

        
        LoginView loginView = new LoginView(memberManager);
        Member loggedInMember = loginView.login(); //로그인 시도
        
         if (loggedInMember instanceof Professor) {
            Professor professor = (Professor) loggedInMember;
            ProfessorView professorView = new ProfessorView(professor);
            professorView.home();
        } else if (loggedInMember instanceof Student) {
            Student student = (Student) loggedInMember;
            // TODO: StudentView studentView = new StudentView(student);
            // TODO: studentView.home();
            System.out.println("(아직 StudentView 없어서 출력만 합니다) " + student.getName() + " 학생님 메인 화면으로 이동합니다!");
        } else if (loggedInMember instanceof Staff) {
            Staff staff = (Staff) loggedInMember;
            // TODO: StaffView staffView = new StaffView(staff);
            // TODO: staffView.home();
            System.out.println("(아직 StaffView 없어서 출력만 합니다) " + staff.getName() + " 직원님 메인 화면으로 이동합니다!");
        } else {
            System.out.println("알 수 없는 사용자 타입입니다.");
        }
    }
}
