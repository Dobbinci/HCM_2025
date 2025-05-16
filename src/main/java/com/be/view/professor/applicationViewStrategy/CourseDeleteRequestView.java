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
            scanner.nextLine(); // 개행 제거

            if (index < 0 || index >= courseDTOs.size()) {
                System.out.println("유효하지 않은 번호입니다.");
                return;
            }

            CourseDTO selectedCourse = courseDTOs.get(index);

            // 삭제 사유 입력 받기
            System.out.print("삭제 요청 사유를 입력하세요: ");
            String reason = scanner.nextLine();

            // 삭제 요청 등록
            professorController.applyDeleteCreatedCourse(
                    selectedCourse.getCourseName(),
                    selectedCourse.getProfessorName(),
                    reason
            );

            System.out.println("삭제 요청이 성공적으로 접수되었습니다.");
        }
    }
}
