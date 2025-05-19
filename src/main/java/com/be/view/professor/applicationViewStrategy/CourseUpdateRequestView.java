package com.be.view.professor.applicationViewStrategy;

import com.be.controller.ProfessorController;
import com.be.dto.CourseDTO;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class CourseUpdateRequestView implements ApplicationViewStrategy {

    private final ProfessorController professorController;

    @Override
    public void show() {
        List<CourseDTO> courseDTOs = professorController.loadCourseList();

        Scanner scanner = new Scanner(System.in);

        if (!courseDTOs.isEmpty()) {
            ApplicationViewStrategy courseViewStrategy = new CourseListView(professorController);
            courseViewStrategy.show();

            System.out.print("수정 요청할 강의의 번호 입력 (1부터 시작): ");
            int index = scanner.nextInt();
            scanner.nextLine(); // 버퍼 정리

            if (index <= 0 || index > courseDTOs.size()) {
                System.out.println("잘못된 번호입니다.");
                return;
            }
            CourseDTO course = courseDTOs.get(--index);
            System.out.print("강의명 : ");
            String courseName = scanner.nextLine();
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
            System.out.print("사유 : ");
            String reason = scanner.nextLine();

            // 실제 수정 요청 전달 (예: courseId를 전달할 수도 있음)
            professorController.requestCourseUpdate(
                    course.getId(), courseName, semester, credit, capacity, classroom, content, reason
            );
        } else {
            System.out.println("현재 등록된 강의가 없습니다.");
        }
    }
}
