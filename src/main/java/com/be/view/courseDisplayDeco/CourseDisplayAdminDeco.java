package com.be.view.courseDisplayDeco;

import com.be.dto.CourseDTO;

import java.util.List;

public class CourseDisplayAdminDeco extends CourseDisplayDecorator {

    public CourseDisplayAdminDeco(CourseDisplayComponent courseDisplayComponent) {
        super(courseDisplayComponent);
    }

    @Override
    public void displayCourse(List<CourseDTO> courseDTOs) {
        super.displayCourse(courseDTOs);
        displayCourseAdmin(courseDTOs);

    }

    public void displayCourseAdmin(List<CourseDTO> courseDTOs) {
        if (!courseDTOs.isEmpty()) {
            int widthNo = 4;
            int widthName = 20;
            int widthProfessor = 15;
            int widthCredit = 8;
            int widthEvaluation = 8;
            int widthFunny = 8;

            int totalWidth = widthNo + widthName + widthProfessor + widthCredit +widthEvaluation + widthFunny + 6 * 4 + 2;

            // 구분선 생성
            String line = String.format("+%s+", "-".repeat(totalWidth - 2));

            // 헤더 출력
            System.out.println(line);
            System.out.printf("| %-" + widthNo + "s | "
                            + "%-" + widthName + "s | "
                            + "%-" + widthProfessor + "s | "
                            + "%-" + widthCredit + "s | "
                            + "%-" + widthEvaluation + "s | "
                            + "%-" + widthFunny + "s |\n",
                    "No", "Course Name", "Professor", "Credit", "Evaluation", "FunnyRate"
            );
            System.out.println(line);

            // 간략한 데이터 출력
            int index = 0;
            for (CourseDTO courseDTO : courseDTOs) {
                System.out.printf("| %" + widthNo + "d | "
                                + "%-" + widthName + "s | "
                                + "%-" + widthProfessor + "s | "
                                + "%-" + widthCredit + "s | "
                                + "%-" + widthEvaluation + "s | "
                                + "%-" + widthFunny + "s |\n",
                        ++index, courseDTO.getCourseName(),
                        courseDTO.getProfessorName(),
                        courseDTO.getCredit(),
                        courseDTO.getEvaluation(),
                        courseDTO.getFunnyRate()
                );
            }

            System.out.println(line);
        }
        else {
            System.out.println("개설된 강의가 없습니다.");
        }
    }
}
