package com.be.view.staff.courseManage;

import com.be.controller.StaffController;
import com.be.model.Staff;
import com.be.view.staff.StaffCourseApplicationViewStrategy;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class CourseCreateView {
    private final EntityManager em;

    public CourseCreateView(EntityManager em) {
        this.em = em;
    }

    public void show(Staff staff) {
        //조회 로직
        StaffCourseApplicationViewStrategy staffCourseApplicationViewStrategy = new StaffCourseApplicationViewStrategy();
        staffCourseApplicationViewStrategy.show(staff);
        StaffController staffController = new StaffController(em);
    
        System.out.println("생성할 강의 선택 : ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        //번호를 넘김.
        staffController.createCourse(choice - 1);
    }
}
