package com.be.controller;
import java.util.List;
import java.util.Scanner;

import com.be.model.*;
import com.be.repository.CourseCreateRequestRepository;
import com.be.repository.CourseDeleteRequestRepository;
import com.be.repository.CourseUpdateRequestRepository;
import com.be.repository.CourseRepository;
import com.be.repository.GenericRepository;
import com.be.repository.impl.*;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StaffController {

    private final EntityManager em;

    //강의 생성 수행 로직
    public void createCourse(Long id) {
        CourseCreateRequestRepository courseApplicationRepo = new CourseCreateRequestRepoImpl(em);
        CourseCreateRequest courseCreateRequest = courseApplicationRepo.findById(id);

        // 강의 신청 객체 생성
        Course course = Course.builder()
                .courseName(courseCreateRequest.getCourseName())
                .professorName(courseCreateRequest.getProfessorName())
                .semester(courseCreateRequest.getSemester())
                .credit(courseCreateRequest.getCredit())
                .capacity(courseCreateRequest.getCapacity())
                .classroom(courseCreateRequest.getClassroom())
                .content(courseCreateRequest.getContent())
                .professor(courseCreateRequest.getProfessor())
                .build();

        // 강의 등록 로직
        CourseRepository courseRepo = new CourseRepoImpl(em);
        courseRepo.save(course);

        System.out.println("강의 등록 완료!\n");
    }


    // 강의 수정 수행 로직
    public void updateCourse(Long id) {
        Scanner scanner = new Scanner(System.in);
        CourseRepoImpl courseRepo = new CourseRepoImpl(em);
        Course editCourse = courseRepo.findById(id);

        String newCourseName;
        String newProfessorName;
        String newSemester;
        String newCredit;
        String newCapacity;
        String newClassroom;
        String newContent;

        //수정 로직
        System.out.println("과목명 수정 : ");
        newCourseName = scanner.nextLine();
        editCourse.setCourseName(newCourseName);

        System.out.println("교수명 수정 : ");
        newProfessorName = scanner.nextLine();
        editCourse.setCourseName(newProfessorName);

        System.out.println("학기 수정 : ");
        newSemester = scanner.nextLine();
        editCourse.setCourseName(newSemester);

        System.out.println("학점 수정 : ");
        newCredit = scanner.nextLine();
        editCourse.setCourseName(newCredit);

        System.out.println("정원 수정 : ");
        newCapacity = scanner.nextLine();
        editCourse.setCourseName(newCapacity);

        System.out.println("강의실 수정 : ");
        newClassroom = scanner.nextLine();
        editCourse.setCourseName(newClassroom);

        System.out.println("내용 수정 : ");
        newContent = scanner.nextLine();
        editCourse.setCourseName(newContent);

        //강의 업데이트
        courseRepo.update(editCourse);
        System.out.println("강의 수정 완료");
    }

    // 교수가 작성한 강의 목록 반환 로직
    public List<CourseCreateRequest> getCourseApplications() {
        CourseCreateRequestRepository courseApplicationRepo = new CourseCreateRequestRepoImpl(em);
        return courseApplicationRepo.findAll();
    }

    // 개설된 강의 목록 반환 로직
    public List<Course> getCreatedCourse() {
        CourseRepoImpl courseRepo = new CourseRepoImpl(em);
        return courseRepo.findAll();
    }
    public List<CourseDeleteRequest> getAllDeleteRequests() {
        CourseDeleteRequestRepoImpl courseDeleteRequestRepo = new CourseDeleteRequestRepoImpl(em);
        return courseDeleteRequestRepo.findAll();
    }

    public void processDeleteRequests(CourseDeleteRequest request) {
        CourseDeleteRequestRepository requestRepo = new CourseDeleteRequestRepoImpl(em);
        CourseRepository courseRepo = new CourseRepoImpl(em);

        if (request != null && request.getCourse() != null) {
            Long courseId = request.getCourse().getId();

            // 요청 삭제
            requestRepo.deleteById(request.getId());

            // 강의 삭제
            courseRepo.delete(courseId);

            System.out.println("강의가 삭제되었습니다.");
        } else {
            System.out.println("삭제 요청이 유효하지 않거나 강의 정보가 없습니다.");
        }
    }

    public List<CourseUpdateRequest> getAllUpdateRequests() {
        CourseUpdateRequestRepoImpl courseUpdateRequestRepo = new CourseUpdateRequestRepoImpl(em);
        return courseUpdateRequestRepo.findAll();
    }

    public void handleUpdateRequests(CourseUpdateRequest request) {
        CourseUpdateRequestRepository requestRepo = new CourseUpdateRequestRepoImpl(em);
        CourseRepository courseRepo = new CourseRepoImpl(em);

        if (request != null) {
            // 수정할 실제 강의 조회
            Course course = request.getCourse();

            if (course != null) {
                em.getTransaction().begin();
                // 강의 정보 갱신
                course.setCourseName(request.getCourseName());
                course.setSemester(request.getSemester());
                course.setCredit(request.getCredit());
                course.setCapacity(request.getCapacity());
                course.setClassroom(request.getClassroom());
                course.setContent(request.getContent());

                em.getTransaction().commit();

                // 요청 삭제
                requestRepo.delete(request);

                System.out.println("강의 수정이 반영되었습니다.");
            } else {
                System.out.println("수정할 강의가 존재하지 않습니다.");
            }
        } else {
            System.out.println("수정 요청이 존재하지 않습니다.");
        }
    }

    //-----------------------------!여기부터 맴버 관리 컨트롤러!-----------------------------//

    //제네릭 사용
    public List<Member> getAllMembers() {
        GenericRepository<Member, Long> memberRepo = new GenericRepoImpl<>(em, Member.class);
        return memberRepo.findAll();
    }

    public List<Professor> getProfessors() {
        GenericRepository<Professor, Long> professorRepo = new GenericRepoImpl<>(em, Professor.class);
        return professorRepo.findAll();
    }

    public List<Staff> getStaffs() {
        GenericRepository<Staff, Long> staffRepo = new GenericRepoImpl<>(em, Staff.class);
        return staffRepo.findAll();
    }

    public List<Student> getStudents() {
        GenericRepository<Student, Long> studentRepo = new GenericRepoImpl<>(em, Student.class);
        return studentRepo.findAll();
    }
}
