package com.be.view.professor.applicationViewStrategy;

import com.be.controller.ProfessorController;
import com.be.dto.CourseApplicationDTO;
import java.util.List;

import com.be.dto.CourseDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseListView implements ApplicationViewStrategy{
    private final ProfessorController professorController;
    @Override
    public void show() {

        List<CourseDTO> courseDTOs = professorController.loadCourseList();
        System.out.println(" -- 나의 강의 목록 -- ");

        //강의 목록 반환 로직
        if (!courseDTOs.isEmpty()) {
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
            for (CourseDTO courseDTO : courseDTOs) {
                System.out.printf("| %" + widthNo + "s | "
                                + "%-" + widthName + "s | "
                                + "%-" + widthProfessor + "s | "
                                + "%-" + widthSemester + "s | "
                                + "%-" + widthCredit + "s | "
                                + "%-" + widthCapacity + "s | "
                                + "%-" + widthClassroom + "s | "
                                + "%-" + widthContent + "s |\n",
                        ++index,//목록 번호는 index값으로(아이디 값 아님)
                        courseDTO.getCourseName(),
                        courseDTO.getProfessorName(),
                        courseDTO.getSemester(),
                        courseDTO.getCredit(),
                        courseDTO.getCapacity(),
                        courseDTO.getClassroom(),
                        courseDTO.getContent()
                );
            }
            System.out.println(line);
        } else {
            System.out.println("신청 목록이 비었습니다.");
        }
    }
}
