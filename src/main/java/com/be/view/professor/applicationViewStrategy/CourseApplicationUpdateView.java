package com.be.view.professor.applicationViewStrategy;
import com.be.controller.ProfessorController;
import com.be.model.Professor;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class CourseApplicationUpdateView implements ApplicationViewStrategy{

    private final ProfessorController professorController;

    @Override
    public void show() {
        // 강의 수정 로직
        Scanner scanner = new Scanner(System.in);
        int index;
            //강의 목록 조회
            ApplicationViewStrategy courseApplicationViewStrategy = new CourseApplicationListView(professorController);
            courseApplicationViewStrategy.show();

            System.out.print("수정할 신청서의 번호 입력 : ");
            index = scanner.nextInt();

            System.out.print("강의명 : ");
            String courseName = scanner.nextLine();
            System.out.print("교수명 : ");
            String professorName = scanner.nextLine();
            System.out.print("학기 : ");
            String semester = scanner.nextLine();
            System.out.print("학점 : ");
            String credit = scanner.nextLine();
            System.out.print("정원 : ");
            String capacity = scanner.nextLine();
            System.out.print("강의실 : ");
            String classroom = scanner.nextLine();
            System.out.print("강의계획서 : ");
            String content = scanner.nextLine();

            professorController.applyUpdateCourse(--index, courseName, professorName, semester, credit, capacity, classroom, content);
        }

}
