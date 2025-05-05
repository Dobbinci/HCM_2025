package com.be.view.professor.applicationViewStrategy;

import com.be.service.Member;
import com.be.service.Professor;
import com.be.view.View;

import java.util.Scanner;

public class CourseCreateApplicationViewStrategy implements ApplicationViewStrategy {

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

        // apply Create course를 실제로 수행하는 메서드
        professor.applyCreateCourse(courseName, professorName, semester, credit, capacity, classroom, content);
    }
}
