package com.be.view.professor;

import com.be.form.CourseApplication;
import com.be.service.Professor;

import java.util.Scanner;

public class ProfessorView {

    private Professor professor;

    public ProfessorView(Professor professor) {
        this.professor = professor;
    }

    public void home() {
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
                ApplyCreateCourseView();
                break;
            case 2:
                System.out.println("강의 삭제");
                break;
            case 3:
                System.out.println("강의 조회");
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
    }

    public void ApplyCreateCourseView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("강의 등록");
        System.out.println("강의명 : ");
        String courseName = scanner.nextLine();
        System.out.println("교수명 : ");
        String professorName = scanner.nextLine();
        System.out.println("학기 : ");
        String semester = scanner.nextLine();
        System.out.println("학점 : ");
        String credit = scanner.nextLine();
        System.out.println("정원 : ");
        String capacity = scanner.nextLine();
        System.out.println("강의실 : ");
        String classroom = scanner.nextLine();
        System.out.println("강의계획서 : ");
        String content = scanner.nextLine();

        // applycreate course를 실제로 수행하는 메서드
        CourseApplication courseApplication = new CourseApplication(courseName, professorName, semester, credit, capacity, classroom, content);
        professor.applyCreateCourse(courseApplication);

    }
}
