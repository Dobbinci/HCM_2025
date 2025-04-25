package com.be.view.professor;

import com.be.control.CourseManager;
import com.be.form.CourseApplication;
import com.be.service.Professor;

import java.util.Scanner;

public class ProfessorView {

    private Professor professor;

    public ProfessorView(Professor professor) {
        this.professor = professor;
    }

    public void home() {
        while (true) { //로그아웃 시 home 로직이 종료될 수 있도록 수정
            System.out.println("메뉴");
            System.out.println("1. 강의 등록");
            System.out.println("2. 강의 삭제");
            System.out.println("3. 강의 조회");
            System.out.println("4. 강의 수정");
            System.out.println("5. 로그아웃");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    applyCreateCourseView();
                    break;
                case 2:
                    applyDeleteCourseView();
                    break;
                case 3:
                    viewCourseApplicationView();
                    break;
                case 4:
                    System.out.println("강의 수정");
                    break;
                case 5:
                    System.out.println("로그아웃");
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
            if (choice == 5) break;
        }
    }

    public void applyCreateCourseView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("강의 등록");
        System.out.print("강의명 : ");
        String courseName = scanner.nextLine();
        System.out.print("교수명 : ");
        String professorName = scanner.nextLine();
        System.out.print("학기 : ");
        String semester = scanner.nextLine();
        System.out.print("학점 : ");
        String credit = scanner.nextLine();
        System.out.print("정원 : ");
        String capacity = scanner.nextLine();
        System.out.print("강의실 : ");
        String classroom = scanner.nextLine();
        System.out.print("강의계획서 : ");
        String content = scanner.nextLine();

        // apply Create course를 실제로 수행하는 메서드
        CourseApplication courseApplication = new CourseApplication(courseName, professorName, semester, credit, capacity, classroom, content);
        professor.applyCreateCourse(courseApplication);
    }

    public void viewCourseApplicationView() {
        System.out.println(" -- 강의 신청서 목록 -- ");
        professor.viewCourseApplication();
    }

    public void applyDeleteCourseView() {
        Scanner scanner = new Scanner(System.in);
        int index;
        if (!CourseManager.getCourseApplications().isEmpty()) {
            // 강의 목록 확인시킨 후 삭제하게 하는거 괜찮은지?
            CourseManager.viewCourseApplication();
            System.out.print("삭제할 신청서의 번호 입력 : ");
            index = scanner.nextInt();
            professor.applyDeleteCourse(index);
        }else{
            System.out.println("삭제할 신청서가 없습니다");
        }
    }
}
