package com.be.view.staff.courseManage;

import com.be.control.CourseManager;
import com.be.model.Course;
import com.be.model.Member;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class CreatedCourseView {
    private final EntityManager em;

    public CreatedCourseView(EntityManager em) {
        this.em = em;
    }

    public void show(Member member) {
        CourseManager manager = CourseManager.getInstance();
        ArrayList<Course> arrayList = manager.getCourseList();
        System.out.println(" -- 개설된 강의 목록 -- ");
        //강의 목록 반환 로직
        if (!arrayList.isEmpty()) {
            int widthNo = 4;
            int widthName = 10;
            int widthProfessor = 10;

            // 전체 너비 계산 (필드 + 구분자)
            int totalWidth = widthNo + widthName + widthProfessor
                    + 9 * 3   // 컬럼 사이 구분자(" | ")
                    + 2;      // 양쪽 테두리("|","|")

            // 구분선 생성
            String line = String.format("+%s+", "-".repeat(totalWidth - 2));

            // 헤더 출력
            System.out.println(line);
            System.out.printf("| %-" + widthNo + "s | "
                            + "%-" + widthName + "s | "
                            + "%-" + widthProfessor + "s |\n",
                    "No", "Course Name", "Professor"
            );
            System.out.println(line);

            // 데이터 출력
            int index = 0;
            for (Course courseList : arrayList) {
                System.out.printf("| %" + widthNo + "s | "
                                + "%-" + widthName + "s | "
                                + "%-" + widthProfessor + "s |\n",
                        ++index,
                        courseList.getName(),
                        courseList.getProfessorName()
                );
            }
            System.out.println(line);
        }
        else {
            System.out.println("신청 목록이 비었습니다.");
        }
    }
}
