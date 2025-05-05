package com.be.view.professor;

import com.be.control.CourseManager;
import com.be.service.Professor;

import java.util.Scanner;

public class CourseUpdateApplicationView {

    public static void show(Professor professor) {
        // 강의 수정 로직
        Scanner scanner = new Scanner(System.in);
        int index;

        if (!CourseManager.getCourseApplications().isEmpty()) {
            //강의 목록 조회
            MyCourseApplicationView.show(professor);

            System.out.print("수정할 신청서의 번호 입력 : ");
            index = scanner.nextInt();

            //수정 요청
            professor.applyUpdateCourse(--index);
        } else {
            System.out.println("신청 목록이 비어있습니다.");
        }
    }
}
