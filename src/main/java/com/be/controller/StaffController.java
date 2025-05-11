package com.be.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.be.control.CourseManager;
import com.be.control.MemberManager;
import com.be.model.Course;
import com.be.model.CourseApplication;
import com.be.model.Member;
import com.be.model.Professor;
import com.be.repository.CourseApplicationRepository;
import com.be.repository.CourseRepository;
import com.be.repository.ProfessorRepository;
import com.be.repository.impl.CourseApplicationRepoImpl;
import com.be.repository.impl.CourseRepoImpl;
import com.be.repository.impl.ProfessorRepoImpl;
import jakarta.persistence.EntityManager;

public class StaffController {
    private final MemberManager memberManager = MemberManager.getInstance();
    private final EntityManager em;
    CourseManager manager = CourseManager.getInstance();
    ArrayList<CourseApplication> arrayList = manager.getCourseApplications();

    public StaffController(EntityManager em) {
        this.em = em;
    }

    //스태프가 강의 생성.
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

    public void updateCourse(int choice) {
        ArrayList<Course> arrayList = manager.getCourseList();

        Scanner scanner = new Scanner(System.in);
        System.out.println(arrayList.get(choice).getName());
        System.out.println(arrayList.get(choice).getProfessorName());

        //수정 로직
        System.out.println("과목명 수정 : ");
        arrayList.get(choice).setName(scanner.next());
        System.out.println("교수명 수정 : ");
        arrayList.get(choice).setProfessorName(scanner.next());
    }

    public List<CourseApplication> getCourseApplications() {
        CourseApplicationRepository courseApplicationRepo = new CourseApplicationRepoImpl(em);
        return courseApplicationRepo.findAll();
    }

    public List<Member> getAllMembers() {
        return memberManager.getAllMembers();
    }

    public List<Professor> getProfessors() {
        ProfessorRepository professorRepo = new ProfessorRepoImpl(em);

        return professorRepo.findAll();
    }

    public List<Member> getStudnets() {
        return memberManager.getStudents();
    }

    public List<Member> getStaffs() {
        return memberManager.getStaffs();
    }


}
