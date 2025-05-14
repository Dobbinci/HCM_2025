package com.be.view.professor.applicationViewStrategy;

import com.be.control.CourseManager;
import com.be.model.Professor;

import java.util.Scanner;

public class CourseApplicationUpdateView implements ApplicationViewStrategy{
    @Override
    public void show(Professor professor) {
        // 강의 수정 로직
        Scanner scanner = new Scanner(System.in);
        int index;
        CourseManager manager = CourseManager.getInstance();
        if (!manager.getCourseApplications().isEmpty()) {
            //강의 목록 조회
            CourseApplicationView.ListView profCourseApplicationViewStrategy = new CourseApplicationView.ListView();
            profCourseApplicationViewStrategy.show(professor);

            System.out.print("수정할 신청서의 번호 입력 : ");
            index = scanner.nextInt();

            //Apply update course
            professor.applyUpdateCourse(--index);
        } else {
            System.out.println("신청 목록이 비어있습니다.");
        }
    }
}
