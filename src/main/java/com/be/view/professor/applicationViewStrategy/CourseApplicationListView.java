package com.be.view.professor.applicationViewStrategy;

import com.be.controller.ProfessorControllerFacade;
import com.be.dto.CourseCreateRequestDTO;
import com.be.model.Professor;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CourseApplicationListView implements ApplicationViewStrategy {

    private final ProfessorControllerFacade professorControllerFacade;
    private final Professor professor;

    @Override
    public void show() {

        List<CourseCreateRequestDTO> courseCreateRequestDTOS = professorControllerFacade.loadCourseApplicationList(professor);
        System.out.println(" -- 강의 신청서 목록 -- ");

        //강의 목록 반환 로직
        if (!courseCreateRequestDTOS.isEmpty()) {
            int widthNo = 4;
            int widthName = 15;
            int widthProfessor = 10;
            int widthSemester = 10;
            int widthCredit = 10;
            int widthCapacity = 10;
            int widthClassroom = 10;
            int widthContent = 10;

            // 전체 너비 계산 (필드 + 구분자)
            int totalWidth = widthNo + widthName + widthProfessor + widthSemester
                    + widthCredit + widthCapacity + widthClassroom + widthContent
                    + 9 * 3   // 컬럼 사이 구분자(" | ")
                    + 2;      // 양쪽 테두리("|","|")

            // 구분선 생성
            String line = String.format("+%s+", "-".repeat(totalWidth - 2));

            // 헤더 출력
            System.out.println(line);
            System.out.printf("| %-" + widthNo + "s | "
                            + "%-" + widthName + "s | "
                            + "%-" + widthProfessor + "s | "
                            + "%-" + widthSemester + "s | "
                            + "%-" + widthCredit + "s | "
                            + "%-" + widthCapacity + "s | "
                            + "%-" + widthClassroom + "s | "
                            + "%-" + widthContent + "s |\n",
                    "No", "Course Name", "Professor", "Semester",
                    "Credit", "Capacity", "Classroom", "Content"
            );
            System.out.println(line);

            // 데이터 출력
            int index = 0;
            for (CourseCreateRequestDTO courseCreateRequestDTO : courseCreateRequestDTOS) {
                System.out.printf("| %" + widthNo + "s | "
                                + "%-" + widthName + "s | "
                                + "%-" + widthProfessor + "s | "
                                + "%-" + widthSemester + "s | "
                                + "%-" + widthCredit + "s | "
                                + "%-" + widthCapacity + "s | "
                                + "%-" + widthClassroom + "s | "
                                + "%-" + widthContent + "s |\n",
                        ++index,
                        courseCreateRequestDTO.getCourseName(),
                        courseCreateRequestDTO.getProfessorName(),
                        courseCreateRequestDTO.getSemester(),
                        courseCreateRequestDTO.getCredit(),
                        courseCreateRequestDTO.getCapacity(),
                        courseCreateRequestDTO.getClassroom(),
                        courseCreateRequestDTO.getContent()
                );
            }
            System.out.println(line);
        }
        else {
            System.out.println("신청 목록이 비었습니다.");
        }
    }
}