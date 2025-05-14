package com.be.view.professor.applicationViewStrategy;

import com.be.controller.ProfessorController;
import com.be.model.Professor;
import lombok.AllArgsConstructor;
import java.util.Scanner;

@AllArgsConstructor
public class CourseAppliationCreateView implements ApplicationViewStrategy {

    ProfessorController professorController;

    @Override
    public void show(Professor professor) {
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

        //Apply create course
        professorController.applyCreateCourse(courseName, professorName, semester, credit, capacity, classroom, content);
    }
    }
}
