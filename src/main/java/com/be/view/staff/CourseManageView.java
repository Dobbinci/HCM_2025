package com.be.view.staff;

import com.be.controller.StaffController;
import com.be.model.*;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class CourseManageView {

    Scanner scanner = new Scanner(System.in);
    private final EntityManager em;
    private final StaffController staffController;


    public CourseManageView(EntityManager em) {
        this.em = em;
        this.staffController = new StaffController(em);
    }

    public void show() {
        String[] menuItems = {
                "1. 강의 생성 요청 관리",
                "2. 강의 수정 요청 관리",
                "3. 강의 삭제 요청 관리",
                "4. 개설된 강의 조회",
                "5. 뒤로가기"
        };

        while (true) {
            System.out.println("메뉴");
            for (String items : menuItems) {
                System.out.println(items);
            }
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    CourseCreateRequestView courseCreateRequestView = new CourseCreateRequestView();
                    courseCreateRequestView.show();
                    break;
                case 2:
                    CourseUpdateRequestView courseUpdateRequestView = new CourseUpdateRequestView();
                    courseUpdateRequestView.show();
                    break;
                case 3:
                    CourseDeleteRequestView courseDeleteRequestView = new CourseDeleteRequestView();
                    courseDeleteRequestView.show();
                    break;
                case 4:
                    CreatedCourseView createdCourseView = new CreatedCourseView();
                    createdCourseView.show();
                    break;
            }
            if (choice == 5) {
                break;
            }
        }
    }

    //강의 생성 요청 관리
    public class CourseCreateRequestView {
        public void show() {

            //교수가 작성한 신청서 조회
            List<CourseApplication> courseApplications = staffController.getCourseApplications();
            if (courseApplications.isEmpty()) {
                System.out.println("강의 개설 신청이 없습니다.");
                return;
            }

            for (int i = 0; i < courseApplications.size(); i++) {
                CourseApplication ca = courseApplications.get(i);
                System.out.printf("[%d]. " +
                        "강의명 : %s |" +
                        "교수명 : %s |" +
                        "강의실 : %s ", i + 1, ca.getCourseName(), ca.getProfessorName(), ca.getClassroom());
                System.out.println();
            }

            System.out.print("생성 요청을 반영할 번호 선택 (-1: 취소): ");
            int index = scanner.nextInt();
            scanner.nextLine(); // 개행 제거

            if (index == -1 || index - 1 >= courseApplications.size()) {
                System.out.println("생성 요청 반영을 취소합니다.");
                return;
            }
            CourseApplication selected = courseApplications.get(index - 1);
            staffController.createCourse(selected);
            System.out.println("강의 생성 요청을 반영하였습니다.");
        }
    }

    //개설된 강의 목록 확인
    public class CreatedCourseView {
        public void show() {

            System.out.println(" -- 개설된 강의 목록 -- ");
            List<Course> courseList = staffController.getCreatedCourse();

            for (int i = 0; i < courseList.size(); i++) {
                Course ca = courseList.get(i);

                System.out.printf("[%d]. " +
                        "강의명 : %s |" +
                        "교수명 : %s |" +
                        "강의실 : %s ", i + 1, ca.getCourseName(), ca.getProfessorName(), ca.getClassroom());
                System.out.println();
            }
        }
    }

    public class CourseUpdateRequestView {

        public void show() {
            List<CourseUpdateRequest> requests = staffController.getAllUpdateRequests();

            if (requests.isEmpty()) {
                System.out.println("수정 요청이 없습니다.");
                return;
            }

            System.out.println("=== 강의 수정 요청 목록 ===");
            for (int i = 0; i < requests.size(); i++) {
                CourseUpdateRequest req = requests.get(i);
                System.out.printf("[%d] 강의명: %s → %s / 학기: %s / 정원: %s / 사유: %s%n",
                        i,
                        req.getCourse().getCourseName(),
                        req.getCourseName(),
                        req.getSemester(),
                        req.getCapacity(),
                        req.getReason());
            }

            System.out.print("수정 요청을 반영할 번호 선택 (-1: 취소): ");
            int index = scanner.nextInt();
            scanner.nextLine(); // 개행 제거

            if (index == -1 || index >= requests.size()) {
                System.out.println("수정 요청 반영을 취소합니다.");
                return;
            }

            CourseUpdateRequest selectedRequest = requests.get(index);
            staffController.handleUpdateRequests(selectedRequest);
            System.out.println("강의 수정 요청을 반영하였습니다.");
        }
    }

    public class CourseDeleteRequestView {

        public void show() {
            List<CourseDeleteRequest> requests = staffController.getAllDeleteRequests();

            if (requests.isEmpty()) {
                System.out.println("수정 요청이 없습니다.");
                return;
            }

            System.out.println("=== 강의 삭제 요청 목록 ===");
            for (int i = 0; i < requests.size(); i++) {
                CourseDeleteRequest req = requests.get(i);
                System.out.printf("[%d] 강의명: %s → %s / 사유: %s%n",
                        i,
                        req.getCourse().getCourseName(),
                        req.getCourseName(),
                        req.getReason());
            }

            System.out.print("삭제 요청을 반영할 번호 선택 (-1: 취소): ");
            int index = scanner.nextInt();
            scanner.nextLine(); // 개행 제거

            if (index == -1 || index >= requests.size()) {
                System.out.println("수정 요청 반영을 취소합니다.");
                return;
            }
            CourseDeleteRequest selectedRequest = requests.get(index);
            staffController.processDeleteRequests(selectedRequest);
            System.out.println("강의 수정 요청을 반영하였습니다.");
        }
    }
}
