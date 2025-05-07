package com.be.view.staff.courseManage;

import com.be.service.Staff;
import com.be.view.staff.StaffCourseApplicationViewStrategy;

import java.util.Scanner;

public class CourseCreateView {
    public static void show(Staff staff) {
        //조회 로직
        StaffCourseApplicationViewStrategy staffCourseApplicationViewStrategy = new StaffCourseApplicationViewStrategy();
        staffCourseApplicationViewStrategy.show(staff);
    
        System.out.println("생성할 강의 선택 : ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        //번호를 넘김.
        staff.createCourse(choice - 1);
    }
}
