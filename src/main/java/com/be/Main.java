package com.be;

import com.be.model.Professor;
import com.be.model.Staff;
//import com.be.view.Authentication.LoginView;
import com.be.model.Student;
import com.be.view.Authentication.LoginView;
import com.be.view.professor.ProfessorHomeView;
import com.be.view.staff.StaffHomeView;
import com.be.model.Member;
import com.be.view.Authentication.TemplateLoginView;
import com.be.view.student.StudentHomeView;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        //로그 안보이게
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        // Persistence Unit "PU"를 기반으로 EntityManagerFactory 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

        // 실제 DB 작업을 수행할 EntityManager 생성 (요청 또는 트랜잭션 단위로 사용)
        EntityManager em = emf.createEntityManager();

        while (true) {
            TemplateLoginView loginView = new LoginView(em);
            Member loggedInMember = loginView.loginOrSignupFlow();

            if (loggedInMember instanceof Professor) {
                ProfessorHomeView professorHomeView = new ProfessorHomeView(em);
                professorHomeView.show();

            } else if (loggedInMember instanceof Student) {
                StudentHomeView studentHomeView = new StudentHomeView(em);
                studentHomeView.show();

            } else if (loggedInMember instanceof Staff staff) {
                StaffHomeView staffHomeView = new StaffHomeView(em);
                staffHomeView.show();

            } else {
                System.out.println("알 수 없는 사용자 타입입니다.");
            }
        }
    }

//        StaffHomeView staffHomeView = new StaffHomeView(em);
//        staffHomeView.show();

//        ProfessorHomeView professorHomeView = new ProfessorHomeView(em);
//       professorHomeView.show();


}
