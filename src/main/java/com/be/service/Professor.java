package com.be.service;

import com.be.control.CourseManager;
import com.be.form.CourseApplication;

public class Professor {
    private String name;
    private String professorId;

    public void applyCreateCourse(CourseApplication courseApplication) {
        // 강의 등록을 위한 정보
        // 강의 등록 유효성 검사
        CourseManager.validateCourseApplication(courseApplication);
        // 강의 등록 로직
        CourseManager.createCourseApplication(courseApplication);
    }

    public void applyUpdateCourse(int index) {
        CourseManager.updateCourseApplication(index);
    }

    public void applyDeleteCourse(int index) {
        CourseManager.deleteCourseApplication(index);
    }
}
