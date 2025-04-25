package com.be.control;

import com.be.form.CourseApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CourseManager {

    private static ArrayList<CourseApplication> courseApplications = new ArrayList<>();

    public static void validateCourseApplication(CourseApplication courseApplication) {
        // 점검 로직
    }

    public static void createCourseApplication(CourseApplication courseApplication) {
        // 강의 등록 로직
        courseApplications.add(courseApplication);
        System.out.println("강의 등록 완료");
    }
}
