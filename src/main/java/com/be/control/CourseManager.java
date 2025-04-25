package com.be.control;

import com.be.form.CourseApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseManager {

    public static ArrayList<CourseApplication> getCourseApplications() {
        return courseApplications;
    }

    private static ArrayList<CourseApplication> courseApplications = new ArrayList<>();

    public static void validateCourseApplication(CourseApplication courseApplication) {
        // 점검 로직
    }

    public static void createCourseApplication(CourseApplication courseApplication) {
        // 강의 등록 로직
        courseApplications.add(courseApplication);
        System.out.println("강의 등록 완료");
    }

    public static void viewCourseApplication() {
        //강의 목록 반환 로직
        if (!courseApplications.isEmpty()) {
            int wNo = 4;
            int wName = 15;
            int wProfessor = 10;
            int wSemester = 10;
            int wCredit = 10;
            int wCapacity = 10;
            int wClassroom = 10;
            int wContent = 10;

            // 전체 너비 계산 (필드 + 구분자)
            int totalWidth = wNo + wName + wProfessor + wSemester
                    + wCredit + wCapacity + wClassroom + wContent
                    + 9 * 3   // 컬럼 사이 구분자(" | ")
                    + 2;      // 양쪽 테두리("|","|")

            // 구분선 생성
            String line = String.format("+%s+",
                    "-".repeat(totalWidth - 2)
            );

            // 헤더 출력
            System.out.println(line);
            System.out.printf("| %-" + wNo + "s | "
                            + "%-" + wName + "s | "
                            + "%-" + wProfessor + "s | "
                            + "%-" + wSemester + "s | "
                            + "%-" + wCredit + "s | "
                            + "%-" + wCapacity + "s | "
                            + "%-" + wClassroom + "s | "
                            + "%-" + wContent + "s |\n",
                    "No", "Course Name", "Professor", "Semester",
                    "Credit", "Capacity", "Classroom", "Content"
            );
            System.out.println(line);

            // 데이터 출력
            int index = 0;
            for (CourseApplication ca : courseApplications) {
                System.out.printf("| %"
                                + wNo + "s | "
                                + "%-" + wName + "s | "
                                + "%-" + wProfessor + "s | "
                                + "%-" + wSemester + "s | "
                                + "%-" + wCredit + "s | "
                                + "%-" + wCapacity + "s | "
                                + "%-" + wClassroom + "s | "
                                + "%-" + wContent + "s |\n",
                        ++index,
                        ca.getCourseName(),
                        ca.getProfessorName(),
                        ca.getSemester(),
                        ca.getCredit(),
                        ca.getCapacity(),
                        ca.getClassroom(),
                        ca.getContent()
                );
            }
            System.out.println(line);
        } else {
            System.out.println("신청 목록이 비었습니다.");
        }
    }

    public static void deleteCourseApplication(int index) {
        // 삭제 로직
        if (!courseApplications.isEmpty()) {
            if (index > 0 && index <= courseApplications.size()) {
                System.out.print(courseApplications.get(--index).getCourseName());
                System.out.println(" 삭제 성공");
                courseApplications.remove(index);
            } else {
                // 예외 처리
                System.out.println("번호를 바르게 입력하세요");
            }
        }else{
            System.out.println("삭제할 값이 없습니다");
        }
    }
}
