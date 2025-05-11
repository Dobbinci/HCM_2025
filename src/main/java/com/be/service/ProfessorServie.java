package com.be.service;

import com.be.control.CourseManager;
import com.be.model.CourseApplication;
import com.be.model.Member;
import jakarta.persistence.Entity;

public class ProfessorService {
    private String professorId;

    // professorId 필드용 getter/setter
    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public void applyCreateCourse(String courseName, String professorName, String semester, String credit, String capacity, String classroom, String content) {
        // 강의 신청 객체 생성
        CourseApplication courseApplication = new CourseApplication(courseName, professorName, semester, credit, capacity, classroom, content);
        // 강의 등록 유효성 검사
        CourseManager.getInstance().validateCourseApplication(courseApplication);
        // 강의 등록 로직
        CourseManager.getInstance().createCourseApplication(courseApplication);
    }

    public void applyUpdateCourse(int index) {
        CourseManager.getInstance().updateCourseApplication(index);
    }

    public void applyDeleteCourse(int index) {
        CourseManager.getInstance().deleteCourseApplication(index);
    }
}
