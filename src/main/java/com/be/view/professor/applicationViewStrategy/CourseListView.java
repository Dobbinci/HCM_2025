package com.be.view.professor.applicationViewStrategy;

import com.be.controller.ProfessorController;

import java.util.List;
import java.util.Scanner;

import com.be.dto.CourseDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseListView implements ApplicationViewStrategy {
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

            System.out.println("강의를 검색하시겠습니가? (Y/N): ");
            String choice;
            Scanner scanner = new Scanner(System.in);
            choice = scanner.next();
            scanner.nextLine();

            if (choice.equals("Y")) {
                System.out.print("과목명을 입력하세요 :");
                String keyword;
                keyword = scanner.nextLine();

                List<CourseDTO> filteredCourses = professorController.search(keyword);

                if (filteredCourses.isEmpty()) {
                    System.out.println("검색 결과가 없습니다.");
                } else {
                    for (int i = 0; i < filteredCourses.size(); i++) {
                        CourseDTO course = filteredCourses.get(i);
                        System.out.printf("[%d]. 강의명: %s | 교수명: %s | 학기: %s | 학점: %s | 정원: %s | 강의실: %s | 강의내용: %s\n",
                                i + 1, course.getCourseName(),
                                course.getProfessorName(), course.getSemester(),
                                course.getCredit(), course.getCapacity(),
                                course.getClassroom(), course.getContent());
                    }
                }
            }
        } else {
            System.out.println("신청 목록이 비었습니다.");
        }
    }
}
