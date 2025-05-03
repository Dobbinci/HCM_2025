package com.be.view.professor;

import com.be.service.Professor;

import java.util.Scanner;

public class ProfessorHomeView {

    public static void show(Professor professor) {
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
                    CourseCreateApplicationView.show(professor);
                    break;
                case 2:
                    MyCourseApplicationView.show(professor);
                    break;
                case 3:
                    CourseUpdateApplicationView.show(professor);
                    break;
                case 4:
                    CourseDeleteApplicationView.show(professor);
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
