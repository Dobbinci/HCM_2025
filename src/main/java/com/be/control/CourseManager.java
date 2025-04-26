package com.be.control;

import com.be.form.CourseApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseManager {

    public static ArrayList<CourseApplication> getCourseApplications() {
        return courseApplications;
    }

    private static ArrayList<CourseApplication> courseApplications = new ArrayList<>();

    public static void validateCourseApplication(CourseApplication courseApplication) {
        // 점검 로직
    }

    public static void createCourseApplication(CourseApplication courseApplication) {
        // 강의 등록 로직
        courseApplications.add(courseApplication);
        System.out.println("강의 등록 완료");
    }

    public static void deleteCourseApplication(int index) {
        // 삭제 로직
        if (!courseApplications.isEmpty()) {
            if (index > 0 && index <= courseApplications.size()) {
                System.out.print(courseApplications.get(--index).getCourseName());
                courseApplications.remove(index);
                System.out.println(" 삭제 성공");
            } else {
                // 예외 처리
                System.out.println("번호를 바르게 입력하세요");
            }
        }else{
            System.out.println("삭제할 값이 없습니다");
        }
    }
}
