package com.be.service;

import com.be.control.CourseManager;
import com.be.form.Course;
import com.be.form.CourseApplication;

import java.util.ArrayList;
import java.util.Scanner;

public class Staff extends Member {

    private String name;
    private String staffId;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public void manageMemberInfo() {
    }

    //스태프가 강의 생성.
    public void createCourse(int choice) {
        CourseManager manager = CourseManager.getInstance();
        ArrayList<CourseApplication> arrayList = manager.getCourseApplications();

        String courseName = arrayList.get(choice).getCourseName();
        String professorName = arrayList.get(choice).getProfessorName();
//        int major;
//        int code;
//        int year;
//        int time;

        // course 저장
        Course course = new Course(courseName, professorName);

        // 강의 등록 로직
        CourseManager.getCourseList().add(course);
        System.out.println("강의 등록 완료!\n");
    }

    public void updateCourse(int choice) {
        ArrayList<Course> arrayList = CourseManager.getCourseList();

        Scanner scanner = new Scanner(System.in);
        System.out.println(arrayList.get(choice).getName());
        System.out.println(arrayList.get(choice).getProfessorName());

        //수정 로직
        System.out.println("과목명 수정 : ");
        arrayList.get(choice - 1).setName(scanner.next());
        System.out.println("교수명 수정 : ");
        arrayList.get(choice - 1).setProfessorName(scanner.next());
    }

}
