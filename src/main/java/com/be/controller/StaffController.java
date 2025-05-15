package com.be.controller;
import java.util.List;
import java.util.Scanner;

import com.be.model.*;
import com.be.repository.CourseApplicationRepository;
import com.be.repository.CourseRepository;
import com.be.repository.GenericRepository;
import com.be.repository.impl.*;
import com.be.service.Student;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StaffController {

    private final EntityManager em;

    //강의 생성 수행 로직
    public void createCourse(Long id) {
        CourseApplicationRepository courseApplicationRepo = new CourseApplicationRepoImpl(em);
        CourseApplication courseApplication = courseApplicationRepo.findById(id);

        // 강의 신청 객체 생성
        Course course = Course.builder()
                .courseName(courseApplication.getCourseName())
                .professorName(courseApplication.getProfessorName())
                .semester(courseApplication.getSemester())
                .credit(courseApplication.getCredit())
                .capacity(courseApplication.getCapacity())
                .classroom(courseApplication.getClassroom())
                .content(courseApplication.getContent())
                .professor(courseApplication.getProfessor())
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
    public List<CourseApplication> getCourseApplications() {
        CourseApplicationRepository courseApplicationRepo = new CourseApplicationRepoImpl(em);
        return courseApplicationRepo.findAll();
    }

    // 개설된 강의 목록 반환 로직
    public List<Course> getCreatedCourse() {
        CourseRepoImpl courseRepo = new CourseRepoImpl(em);
        return courseRepo.findAll();
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

    public List<Student> getStudnets() {
        GenericRepository<Student, Long> studentRepo = new GenericRepoImpl<>(em, Student.class);
        return studentRepo.findAll();
    }
}
