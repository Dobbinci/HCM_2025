package com.be;

import com.be.model.Professor;
import com.be.model.Staff;
import com.be.service.Student;
import com.be.view.LoginView;
import com.be.view.professor.ProfessorHomeView;
import com.be.view.staff.StaffHomeView;
import com.be.model.Member;
import com.be.view.TemplateLoginView;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        // Persistence Unit "PU"를 기반으로 EntityManagerFactory 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

        // 실제 DB 작업을 수행할 EntityManager 생성 (요청 또는 트랜잭션 단위로 사용)
        EntityManager em = emf.createEntityManager();

        while(true) {
        TemplateLoginView loginView = new LoginView();
        Member loggedInMember = loginView.loginOrSignupFlow();

            if (loggedInMember instanceof Professor professor) {
                ProfessorHomeView professorHomeView = new ProfessorHomeView();
                professorHomeView.show(professor);

            } else if (loggedInMember instanceof Student) {
                Student student = (Student) loggedInMember;
                // TODO: StudentView studentView = new StudentView(student);
                // TODO: studentView.home();

            } else if (loggedInMember instanceof Staff staff) {
                StaffHomeView staffHomeView = new StaffHomeView(em);
                staffHomeView.show(staff);

            } else {
                System.out.println("알 수 없는 사용자 타입입니다.");
            }
        }
    }
}