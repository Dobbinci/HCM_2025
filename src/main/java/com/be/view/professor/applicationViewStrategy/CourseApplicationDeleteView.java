package com.be.view.professor.applicationViewStrategy;

import com.be.controller.ProfessorControllerFacade;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class CourseApplicationDeleteView implements ApplicationViewStrategy {

    private final ProfessorControllerFacade professorControllerFacade;

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        //강의 목록 조회
        ApplicationViewStrategy courseApplicationViewStrategy = new CourseApplicationListView(professorControllerFacade);
        courseApplicationViewStrategy.show();

        System.out.print("삭제할 신청서의 번호 입력 : ");
        int index = scanner.nextInt();

        //Apply delete course
        professorControllerFacade.applyDeleteCourse(--index);
    }

}
