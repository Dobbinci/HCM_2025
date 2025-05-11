package com.be.view.staff.courseManage;

import com.be.model.Staff;

import java.util.Scanner;

public class CourseUpdateView {
    public static void show(Staff staff) {
        //생성한 강의 조회 로직
        CreatedCourseView createdCourseView = new CreatedCourseView();
        createdCourseView.show(staff);

        System.out.println("수정할 강의 번호 입력 : ");
        Scanner scanner = new Scanner(System.in);
        int choice;
        choice = scanner.nextInt();
        staff.updateCourse(choice - 1);
    }
}
