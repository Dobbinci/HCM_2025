package com.be.view.student;

import com.be.controller.StaffController;
import com.be.controller.StudentController;
import com.be.dto.CourseCreateRequestDTO;
import com.be.dto.EnrolledCourseDTO;
import com.be.view.staff.CourseManageView;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class StudentHomeView {
    Scanner scanner = new Scanner(System.in);
    EntityManager em;
    StudentController studentController;

    public StudentHomeView(EntityManager em) {
        this.em = em;
        this.studentController = new StudentController(em);
    }

    public void show() {
        String[] menuItems = {
                "1. 강의 등록",
                "2. 강의 삭제",
                "3. 등록한 강의 조회",
                "4. 뒤로가기"
        };

        while (true) {
            System.out.println("메뉴");
            for (String items : menuItems) {
                System.out.println(items);
            }
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    EnrollCourseView enrollCourseView = new EnrollCourseView();
                    enrollCourseView.show();
                    break;
                case 2:
                    DropCourseView dropCourseView = new DropCourseView();
                    dropCourseView.show();
                    break;
                case 3:
                    EnrollCourseListView enrollCourseListView = new EnrollCourseListView();
                    enrollCourseListView.show();
                    break;
            }
            if (choice == 4) {
                break;
            }
        }
    }

    public class EnrollCourseView {
        public void show() {
            CourseManageView courseManageView = new CourseManageView(em);
            CourseManageView.CreatedCourseView createdCourseView = courseManageView.new CreatedCourseView();
            createdCourseView.show();
            System.out.println("등록할 강의 번호 선택 : ");
            int choice;
            choice = scanner.nextInt();

            studentController.enrollCourse(choice - 1);
        }
    }

    public class EnrollCourseListView {
        public void show() {
            List<EnrolledCourseDTO> enrolledCourseDTOS = studentController.loadEnrolledCourseList();

            for (int i = 0; i < enrolledCourseDTOS.size(); i++) {
                EnrolledCourseDTO dto = enrolledCourseDTOS.get(i);
                System.out.printf("[%d] 강의명: %s | 교수명: %s | 학기: %s | 학점: %s | 정원: %s | 강의실: %s | 강의 내용: %s%n",
                        i + 1,
                        dto.getCourseName(),
                        dto.getProfessorName(),
                        dto.getSemester(),
                        dto.getCredit(),
                        dto.getCapacity(),
                        dto.getClassroom(),
                        dto.getContent()
                );
            }
        }
    }

    public class DropCourseView {
        public void show() {
            EnrollCourseListView enrollCourseListView = new EnrollCourseListView();
            enrollCourseListView.show();
            System.out.println("삭제할 강의 번호 선택 : ");
            int choice;
            choice = scanner.nextInt();

            studentController.dropCourse(choice - 1);
        }
    }
}
