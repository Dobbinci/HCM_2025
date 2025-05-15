package com.be.view.professor.applicationViewStrategy;

import com.be.control.CourseManager;
import com.be.controller.ProfessorController;
import com.be.model.CourseApplication;
import com.be.model.Professor;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class CourseApplicationDeleteView implements ApplicationViewStrategy {

    private final ProfessorController professorController;

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);

        int index;
        CourseManager manager = CourseManager.getInstance();
        if (!manager.getCourseApplications().isEmpty()) {
            //강의 목록 조회
            ApplicationViewStrategy courseApplicationViewStrategy = new CourseApplicationListView(professorController);
            courseApplicationViewStrategy.show();

            System.out.print("삭제할 신청서의 번호 입력 : ");
            index = scanner.nextInt();

            //Apply delete course
            professorController.applyDeleteCourse(--index);
        } else {
            System.out.println("신청 목록이 비어있습니다.");
        }
    }
}
