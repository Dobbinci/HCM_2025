package com.be.controller;


//import com.be.control.CourseManager;


import com.be.dto.CourseCreateRequestDTO;
import com.be.dto.CourseDTO;
import com.be.model.*;
import com.be.repository.*;
import com.be.repository.impl.*;
import lombok.AllArgsConstructor;
import jakarta.persistence.EntityManager;

import java.util.List;

@AllArgsConstructor
public class ProfessorController implements BaseController {

    private final EntityManager em;

    public void applyCreateCourse(String courseName, String professorName, String semester, String credit, String capacity, String classroom, String content) {

        CourseCreateRequestRepository courseApplicationRepo = new CourseCreateRequestRepoImpl(em);
        GenericRepository<Professor, Long> memberRepo = new GenericRepoImpl<>(em, Professor.class);

        Professor professor = memberRepo.findById(1L); // 예시로 1L을 사용, 실제로는 교수 ID를 받아와야 함

        // 강의 신청 객체 생성
        CourseCreateRequest courseCreateRequest = CourseCreateRequest.builder()
                .courseName(courseName)
                .professorName(professorName)
                .semester(semester)
                .credit(credit)
                .capacity(capacity)
                .classroom(classroom)
                .content(content)
                .professor(professor)
                .build();

        // 강의 등록 유효성 검사
        //CourseManager.getInstance().validateCourseApplication(courseApplication);
        // 강의 등록 로직
        courseApplicationRepo.save(courseCreateRequest);

        System.out.println("강의 등록 신청 완료!\n");
    }

    public List<CourseCreateRequestDTO> loadCourseApplicationList() {
        CourseCreateRequestRepository courseApplicationRepo = new CourseCreateRequestRepoImpl(em);
        // 강의 신청 목록 조회
        List<CourseCreateRequest> courseCreateRequestList = courseApplicationRepo.findByProfessorId(1L); // 예시로 1L을 사용, 실제로는 교수 ID를 받아와야 함

        // DTO로 변환
        return courseCreateRequestList.stream()
                .map(courseCreateRequest -> new CourseCreateRequestDTO(
                        courseCreateRequest.getId(),
                        courseCreateRequest.getCourseName(),
                        courseCreateRequest.getProfessorName(),
                        courseCreateRequest.getSemester(),
                        courseCreateRequest.getCredit(),
                        courseCreateRequest.getCapacity(),
                        courseCreateRequest.getClassroom(),
                        courseCreateRequest.getContent(),
                        courseCreateRequest.getProfessor().getId())).toList();
    }

    public List<CourseDTO> loadCourseList() {
        CourseRepository courseRepo = new CourseRepoImpl(em);
        // 나의 강의 목록 조회
        List<Course> courseList = courseRepo.findByProfessorId(1L); // 예시로 1L을 사용, 실제로는 교수 ID를 받아와야 함

        // DTO로 변환
        return courseList.stream()
                .map(course -> new CourseDTO(
                        course.getId(),
                        course.getCourseName(),
                        course.getProfessorName(),
                        course.getSemester(),
                        course.getCredit(),
                        course.getCapacity(),
                        course.getClassroom(),
                        course.getContent(),
                        course.getProfessor().getId())).toList();
    }

    public void applyUpdateCourse(int index, String courseName, String professorName, String semester, String credit, String capacity, String classroom, String content) {
        CourseCreateRequestRepository courseApplicationRepo = new CourseCreateRequestRepoImpl(em);
        // 수정할 강의신청 객체 불러오기
        CourseCreateRequest courseCreateRequest = courseApplicationRepo.findByProfessorId(1L).get(index); // 예시로 1L을 사용, 실제로는 교수 ID를 받아와야 함

        if (courseCreateRequest != null) {
            // 강의신청 수정 로직
            courseCreateRequest.setCourseName(courseName);
            courseCreateRequest.setProfessorName(professorName);
            courseCreateRequest.setSemester(semester);
            courseCreateRequest.setCredit(credit);
            courseCreateRequest.setCapacity(capacity);
            courseCreateRequest.setClassroom(classroom);
            courseCreateRequest.setContent(content);

            courseApplicationRepo.update(courseCreateRequest);
            System.out.println("강의 수정 완료!\n");
        } else {
            System.out.println("해당 강의 신청서가 존재하지 않습니다.");
        }
    }


    public void applyDeleteCourse(int index) {
        CourseCreateRequestRepository courseApplicationRepo = new CourseCreateRequestRepoImpl(em);
        CourseCreateRequest courseCreateRequest = courseApplicationRepo.findByProfessorId(1L).get(index); // 예시로 1L을 사용, 실제로는 교수 ID를 받아와야 함
        if (courseCreateRequest != null) {
            // 강의신청 삭제 로직
            courseApplicationRepo.delete(courseCreateRequest.getId());
            System.out.println("강의 삭제 완료!\n");
        } else {
            System.out.println("해당 강의 신청서가 존재하지 않습니다.");
        }
    }

    public void requestCourseUpdate(Long courseId, String newCourseName, String semester, String credit,
                                    String capacity, String classroom, String content, String reason) {

        CourseRepository courseRepo = new CourseRepoImpl(em);
        Course course = courseRepo.findById(courseId);
        CourseUpdateRequestRepository updateRepo = new CourseUpdateRequestRepoImpl(em);

        CourseUpdateRequest request = CourseUpdateRequest.builder()
                .course(course)
                .professor(course.getProfessor()) // 또는 현재 로그인한 교수
                .courseName(newCourseName)
                .semester(semester)
                .credit(credit)
                .capacity(capacity)
                .classroom(classroom)
                .content(content)
                .reason(reason)
                .build();

        updateRepo.save(request);
        System.out.println("강의 수정 요청이 제출되었습니다.");
    }


    public void applyDeleteCreatedCourse(Long courseId, String courseName, String professorName, String reason) {
        CourseDeleteRequestRepository repo = new CourseDeleteRequestRepoImpl(em);
        GenericRepository<Professor, Long> memberRepo = new GenericRepoImpl<>(em, Professor.class);
        CourseRepository courseRepo = new CourseRepoImpl(em);

        CourseDeleteRequest request = CourseDeleteRequest.builder()
                .courseName(courseName)
                .professorName(professorName)
                .reason(reason)
                .course(courseRepo.findById(courseId))
                .professor(memberRepo.findById(1L)) // 예시로 1L을 사용, 실제로는 교수 ID를 받아와야 함
                .build();

        repo.save(request);

        System.out.println("강의 삭제 요청이 등록되었습니다.");
    }

    public List<CourseDTO> search(String keyword) {
        List<CourseDTO> courseList = loadCourseList();
        return courseList.stream()
                .filter(course -> course.getCourseName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

}
