package com.be.view.professor.applicationViewStrategy;

import com.be.controller.ProfessorControllerFacade;
import lombok.AllArgsConstructor;
import java.util.Scanner;

@AllArgsConstructor
public class CourseApplicationCreateView implements ApplicationViewStrategy {

    private final ProfessorControllerFacade professorControllerFacade;

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" -- 강의 등록 -- \n");
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

        professorControllerFacade.applyCreateCourse(courseName, professorName, semester, credit, capacity, classroom, content);
    }
}
