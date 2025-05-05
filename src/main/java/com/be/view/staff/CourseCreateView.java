package com.be.view.staff;

import com.be.control.CourseManager;
import com.be.form.Course;
import com.be.form.CourseApplication;
import com.be.service.Professor;
import com.be.service.Staff;
import com.be.view.professor.applicationViewStrategy.ApplicationViewStrategy;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseCreateView {
    public static void show(Staff staff) {
        // 조회 로직
        //...

        System.out.println("생성할 강의 선택 : ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        //번호를 넘김.
        staff.createCourse(choice - 1);
    }
}
