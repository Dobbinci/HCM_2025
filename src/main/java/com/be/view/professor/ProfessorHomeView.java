package com.be.view.professor;

import com.be.service.Professor;
import com.be.view.View;

import java.util.Scanner;

public class ProfessorHomeView implements View {

    private Professor professor;

    public ProfessorHomeView(Professor professor) {
        this.professor = professor;
    }

    @Override
    public void show() {
        while (true) { //로그아웃 시 home 로직이 종료될 수 있도록 수정
            System.out.println("메뉴");
            System.out.println("1. 강의 등록");
            System.out.println("2  강의 조회");
            System.out.println("3. 강의 수정");
            System.out.println("4. 강의 삭제");
            System.out.println("5. 로그아웃");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    CourseCreateApplicationView courseCreateApplicationView = new CourseCreateApplicationView(professor);
                    courseCreateApplicationView.show();
                    break;
                case 2:
                    MyCourseApplicationView myCourseApplicationView = new MyCourseApplicationView(professor);
                    myCourseApplicationView.show();
                    break;
                case 3:
                    CourseUpdateApplicationView courseUpdateApplicationView = new CourseUpdateApplicationView(
                            professor,
                            new MyCourseApplicationView(professor)

                    );
                    courseUpdateApplicationView.show();
                    break;
                case 4:
                    CourseDeleteApplicationView courseDeleteApplicationView = new CourseDeleteApplicationView(
                            professor,
                            new MyCourseApplicationView(professor)
                    );
                    courseDeleteApplicationView.show();
                    break;
                case 5:
                    System.out.println("로그아웃");
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다.");
            }
            if (choice == 5) break;
        }
    }
}
