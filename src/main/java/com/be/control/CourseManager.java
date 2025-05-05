package com.be.control;

import com.be.form.Course;
import com.be.form.CourseApplication;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseManager {

    private static final CourseManager instance = new CourseManager();
    private final ArrayList<CourseApplication> courseApplications;
    private CourseManager(){
        courseApplications = new ArrayList<>();
    }
    public static CourseManager getInstance(){
        return instance;
    }

    public ArrayList<CourseApplication> getCourseApplications() {
        return courseApplications;
    }

    private static ArrayList<Course> courseList = new ArrayList<>();//개설된 강의 보관
    public static ArrayList<Course> getCourseList() {return courseList;}

    public static void validateCourseApplication(CourseApplication courseApplication) {
        // 점검 로직
    }

    public void createCourseApplication(CourseApplication courseApplication) {
        // 강의 등록 로직
        courseApplications.add(courseApplication);
        System.out.println("강의 등록 완료!\n");
    }

    public void updateCourseApplication(int index) {
        // 수정 로직
        if (!courseApplications.isEmpty()) {
            if (index >= 0 && index < courseApplications.size()) {
                Scanner scanner = new Scanner(System.in);
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
                System.out.print("강의 내용 : ");
                String content = scanner.nextLine();

                CourseApplication courseApplication = new CourseApplication(courseName, professorName, semester, credit, capacity, classroom, content);

                courseApplications.set(index, courseApplication);

            } else {
                // 예외 처리
                System.out.println("번호를 바르게 입력하세요");
            }
        } else {
            System.out.println("수정할 값이 없습니다");
        }
    }

    public void deleteCourseApplication(int index) {
        // 삭제 로직
        if (!courseApplications.isEmpty()) {
            if (index >= 0 && index < courseApplications.size()) {
                System.out.print(courseApplications.get(index).getCourseName());
                courseApplications.remove(index);
                System.out.println(" 삭제 성공");
            } else {
                // 예외 처리
                System.out.println("번호를 바르게 입력하세요");
            }
        } else {
            System.out.println("삭제할 값이 없습니다");
        }
    }

}
