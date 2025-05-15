package com.be.controller;

import com.be.control.CourseManager;
import com.be.dto.CourseApplicationDTO;
import com.be.model.CourseApplication;
import com.be.repository.CourseApplicationRepository;
import com.be.repository.impl.CourseApplicationRepoImpl;
import lombok.AllArgsConstructor;
import jakarta.persistence.EntityManager;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

    public List<CourseApplicationDTO> loadCourseApplicationList() {
        CourseApplicationRepository courseApplicationRepo = new CourseApplicationRepoImpl(em);
        // 강의 신청 목록 조회
        List<CourseApplication> courseApplicationList = courseApplicationRepo.findByProfessorId(1L); // 예시로 1L을 사용, 실제로는 교수 ID를 받아와야 함

        // DTO로 변환
        return courseApplicationList.stream()
                .map(courseApplication -> new CourseApplicationDTO(
                        courseApplication.getId(),
                        courseApplication.getCourseName(),
                        courseApplication.getProfessorName(),
                        courseApplication.getSemester(),
                        courseApplication.getCredit(),
                        courseApplication.getCapacity(),
                        courseApplication.getClassroom(),
                        courseApplication.getContent())).toList();
    }

    public void applyUpdateCourse(int index, String courseName, String professorName, String semester, String credit, String capacity, String classroom, String content) {
        CourseApplicationRepository courseApplicationRepo = new CourseApplicationRepoImpl(em);
        // 수정할 강의신청 객체 불러오기
        CourseApplication courseApplication = courseApplicationRepo.findByProfessorId(1L).get(index); // 예시로 1L을 사용, 실제로는 교수 ID를 받아와야 함

        if (courseApplication != null) {
            // 강의신청 수정 로직
            courseApplication.setCourseName(courseName);
            courseApplication.setProfessorName(professorName);
            courseApplication.setSemester(semester);
            courseApplication.setCredit(credit);
            courseApplication.setCapacity(capacity);
            courseApplication.setClassroom(classroom);
            courseApplication.setContent(content);

            courseApplicationRepo.update(courseApplication);
            System.out.println("강의 수정 완료!\n");
        } else {
            System.out.println("해당 강의 신청서가 존재하지 않습니다.");
        }
    }

    public void applyDeleteCourse(int index) {
        CourseManager.getInstance().deleteCourseApplication(index);
    }
}
