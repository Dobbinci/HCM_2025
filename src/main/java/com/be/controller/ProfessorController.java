package com.be.controller;

import com.be.control.CourseManager;
import com.be.model.CourseApplication;
import com.be.repository.CourseApplicationRepository;
import com.be.repository.impl.CourseApplicationRepoImpl;
import lombok.AllArgsConstructor;
import jakarta.persistence.EntityManager;

@AllArgsConstructor
public class ProfessorController {

    private final EntityManager em;

    public void applyCreateCourse(String courseName, String professorName, String semester, String credit, String capacity, String classroom, String content) {

        CourseApplicationRepository courseApplicationRepo = new CourseApplicationRepoImpl(em);
        // 강의 신청 객체 생성
        CourseApplication courseApplication = CourseApplication.builder()
                .courseName(courseName)
                .professorName(professorName)
                .semester(semester)
                .credit(credit)
                .capacity(capacity)
                .classroom(classroom)
                .content(content)
                .build();

        // 강의 등록 유효성 검사
        //CourseManager.getInstance().validateCourseApplication(courseApplication);
        // 강의 등록 로직
        courseApplicationRepo.save(courseApplication);

        System.out.println("강의 등록 신청 완료!\n");
    }

    public void applyUpdateCourse(int index) {
        CourseManager.getInstance().updateCourseApplication(index);
    }

    public void applyDeleteCourse(int index) {
        CourseManager.getInstance().deleteCourseApplication(index);
    }
}
