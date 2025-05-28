package com.be.view.student;


import com.be.controller.StudentControllerFacade;
import com.be.dto.CourseDTO;
import com.be.dto.EnrolledCourseDTO;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import com.be.view.textModeChangeView;

import java.util.List;

@AllArgsConstructor
public class StudentHomeView {
    private final EntityManager em;
    private final StudentControllerFacade studentControllerFacade;

    public void show() {

        String[] menuItems = {
                "1. 수강 신청",
                "2. 수강 취소",
                "3. 수강목록 조회",
                "4. 출력 모드 변경",
                "5. 로그 아웃"
        };

        while (true) {
            System.out.println("메뉴");
            for (String items : menuItems) {
                System.out.println(items);
            }
            int choice = new java.util.Scanner(System.in).nextInt();

            switch (choice) {
                case 1:
                    CourseEnrollView();
                    break;
                case 2:
                    CourseDropView();
                    break;
                case 3:
                    EnrolledCourseListView();
                    break;
                case 4:
                    textModeChangeView.show();
                case 5:
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    public void CourseEnrollView() {
        List<CourseDTO> courseDTOs = studentControllerFacade.loadCourseList();
        if (!courseDTOs.isEmpty()) {
            int widthNo = 4;
            int widthName = 15;
            int widthProfessor = 10;
            int widthSemester = 10;
            int widthCredit = 10;
            int widthCapacity = 10;
            int widthClassroom = 10;
            int widthContent = 10;

            int totalWidth = widthNo + widthName + widthProfessor + widthSemester
                    + widthCredit + widthCapacity + widthClassroom + widthContent
                    + 9 * 3
                    + 2;
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
                        ++index, courseDTO.getCourseName(),
                        courseDTO.getProfessorName(), courseDTO.getSemester(),
                        courseDTO.getCredit(), courseDTO.getCapacity(),
                        courseDTO.getClassroom(), courseDTO.getContent()
                );
            }
            System.out.println(line);
            System.out.println("수강 신청할 강의의 번호를 입력하세요: ");
            int courseIndex = new java.util.Scanner(System.in).nextInt() - 1;
            if (courseIndex >= 0 && courseIndex < courseDTOs.size()) {
                studentControllerFacade.enrollCourse(courseIndex);
                System.out.println("강의 수강 신청 완료!\n");
            } else {
                System.out.println("잘못된 번호입니다.");
            }

        }
    }

    public void CourseDropView() {
        if(EnrolledCourseListView()) {
            System.out.println("수강 취소할 강의의 번호를 입력하세요: ");
            int courseIndex = new java.util.Scanner(System.in).nextInt() - 1;
            if (courseIndex >= 0) {
                studentControllerFacade.dropCourse(courseIndex);
            } else {
                System.out.println("잘못된 번호입니다.");
            }
        }
    }

    public boolean EnrolledCourseListView() {
        List<EnrolledCourseDTO> enrolledCourseDTOs = studentControllerFacade.loadEnrolledCourseList();
        if (!enrolledCourseDTOs.isEmpty()) {
            int widthNo = 4;
            int widthName = 15;
            int widthProfessor = 10;
            int widthSemester = 10;
            int widthCredit = 10;
            int widthCapacity = 10;
            int widthClassroom = 10;
            int widthContent = 10;

            int totalWidth = widthNo + widthName + widthProfessor + widthSemester
                    + widthCredit + widthCapacity + widthClassroom + widthContent
                    + 9 * 3
                    + 2;
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
            for (EnrolledCourseDTO enrolledCourseDTO : enrolledCourseDTOs) {
                System.out.printf("| %" + widthNo + "s | "
                                + "%-" + widthName + "s | "
                                + "%-" + widthProfessor + "s | "
                                + "%-" + widthSemester + "s | "
                                + "%-" + widthCredit + "s | "
                                + "%-" + widthCapacity + "s | "
                                + "%-" + widthClassroom + "s | "
                                + "%-" + widthContent + "s |\n",
                        ++index, enrolledCourseDTO.getCourseName(),
                        enrolledCourseDTO.getProfessorName(), enrolledCourseDTO.getSemester(),
                        enrolledCourseDTO.getCredit(), enrolledCourseDTO.getCapacity(),
                        enrolledCourseDTO.getClassroom(), enrolledCourseDTO.getContent()
                );
            }
            System.out.println(line);
            return true;
        } else {
            System.out.println("수강 신청한 강의가 없습니다.");

            return false;
        }

    }
}
