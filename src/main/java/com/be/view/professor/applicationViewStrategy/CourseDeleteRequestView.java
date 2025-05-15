package com.be.view.professor.applicationViewStrategy;

import com.be.controller.ProfessorController;
import com.be.dto.CourseDTO;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class CourseDeleteRequestView implements ApplicationViewStrategy{

    private final ProfessorController professorController;

    @Override
    public void show() {
        List<CourseDTO> courseDTOs = professorController.loadCourseList();
        Scanner scanner = new Scanner(System.in);
        int index;

        if (!courseDTOs.isEmpty()) {
            ApplicationViewStrategy courseViewStrategy = new CourseListView(professorController);
            courseViewStrategy.show();

            System.out.print("삭제 요청할 신청서의 번호 입력 : ");
            index = scanner.nextInt();


        }
    }
}
