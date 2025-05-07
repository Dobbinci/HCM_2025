package com.be.view.professor.applicationViewStrategy;

import com.be.control.CourseManager;
import com.be.service.Member;
import com.be.service.Professor;
import com.be.view.professor.ProfCourseApplicationViewStrategy;

import java.util.Scanner;

public class CourseDeleteApplicationViewStrategy implements ApplicationViewStrategy {

    public void show(Member member) {
        Scanner scanner = new Scanner(System.in);
        Professor professor = (Professor) member;

        int index;
        CourseManager manager = CourseManager.getInstance();
        if (!manager.getCourseApplications().isEmpty()) {
            //강의 목록 조회
            ProfCourseApplicationViewStrategy profCourseApplicationViewStrategy = new ProfCourseApplicationViewStrategy();
            profCourseApplicationViewStrategy.show(professor);

            System.out.print("삭제할 신청서의 번호 입력 : ");
            index = scanner.nextInt();

            //삭제 요청
            professor.applyDeleteCourse(--index);
        } else {
            System.out.println("신청 목록이 비어있습니다.");
        }
    }
}
