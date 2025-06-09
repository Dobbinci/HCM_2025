package com.be.controller;
import java.lang.reflect.Array;
import java.util.List;

import com.be.dto.CourseCreateRequestDTO;
import com.be.dto.CourseDTO;
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
public class StaffControllerFacade implements BaseController{

    private final EntityManager em;

    //강의 생성 수행 로직
    public void createCourse(CourseCreateRequestDTO selected) {
        CourseCreateRequestRepository courseCreateRequestRepo = new CourseCreateRequestRepoImpl(em);
        GenericRepository<Professor, Long> memberRepo = new GenericRepoImpl<>(em, Professor.class);

        // 1-5까지 평가 랜덤저장
        int evaluationScore = (int) (Math.random() * 5) + 1; // 1부터 5까지의 랜덤 숫자


        // 재미도 랜덤저장
        String[] funnyRates = {"노잼", "그럭저럭", "재밌음", "꿀잼", "최고"};
        String funnyRate = funnyRates[(int) (Math.random() * funnyRates.length)];


        // 강의 신청 객체 생성
        Course course = Course.builder()
                .courseName(selected.getCourseName())
                .professorName(selected.getProfessorName())
                .semester(selected.getSemester())
                .credit(selected.getCredit())
                .capacity(selected.getCapacity())
                .classroom(selected.getClassroom())
                .content(selected.getContent())
                .evaluation(String.valueOf(evaluationScore))
                .funnyRate(funnyRate)
                .professor(memberRepo.findById(selected.getProfessorId()))
                .build();

        // 요청 삭제
        courseCreateRequestRepo.delete(selected.getId());

        // 강의 등록 로직
        CourseRepository courseRepo = new CourseRepoImpl(em);
        courseRepo.save(course);

        System.out.println("강의 등록 완료!\n");
    }

    // 교수가 작성한 강의 목록 반환 로직
    public List<CourseCreateRequestDTO> loadCourseApplicationList() {
        CourseCreateRequestRepository courseApplicationRepo = new CourseCreateRequestRepoImpl(em);
        List<CourseCreateRequest> courseCreateRequestList = courseApplicationRepo.findAll();

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

    // 개설된 강의 목록 반환 로직
    public List<CourseDTO> loadCourseList() {
        CourseRepository courseRepo = new CourseRepoImpl(em);
        // 모든 강의 조회
        List<Course> courseList = courseRepo.findAll();
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
                        course.getEvaluation(),
                        course.getFunnyRate(),
                        course.getProfessor().getId())).toList();
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

    public void createCourseFacade(String semester){
        createChapel(semester);
        createChapelEN(semester);
        createCLT(semester); //Community &Leadership Training

        System.out.println("강의 등록 완료!\n");
    }

    private void createChapel(String semester){
        for (int i=1; i<9; i++) {
            Course course = Course.builder()
                    .courseName("채플" + i)
                    .professorName("한동대")
                    .semester(semester)
                    .credit("0")
                    .capacity("999")
                    .classroom("HCA")
                    .content("채플")
                    .build();

            // 강의 등록 로직
            CourseRepository courseRepo = new CourseRepoImpl(em);
            courseRepo.save(course);
        }
    }

    private void createChapelEN(String semester){
        for (int i=1; i<9; i++) {
            Course course = Course.builder()
                    .courseName("English Chapel" + i)
                    .professorName("HGU")
                    .semester(semester)
                    .credit("0")
                    .capacity("999")
                    .classroom("HCA")
                    .content("English Chapel")
                    .build();

            // 강의 등록 로직
            CourseRepository courseRepo = new CourseRepoImpl(em);
            courseRepo.save(course);
        }
    }

    private void createCLT(String semester){
        for (int i=1; i<9; i++) {
            Course course = Course.builder()
                    .courseName("공동체리더십훈련" + i)
                    .professorName("Unconfirmed")
                    .semester(semester)
                    .credit(".5")
                    .capacity("999")
                    .content("팀모임")
                    .build();

            // 강의 등록 로직
            CourseRepository courseRepo = new CourseRepoImpl(em);
            courseRepo.save(course);
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
