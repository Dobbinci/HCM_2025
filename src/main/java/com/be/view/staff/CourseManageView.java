package com.be.view.staff;

import com.be.controller.StaffControllerFacade;
import com.be.dto.CourseCreateRequestDTO;
import com.be.dto.CourseDTO;
import com.be.model.*;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class CourseManageView {

    Scanner scanner = new Scanner(System.in);
    private final EntityManager em;
    private final StaffControllerFacade staffControllerFacade;

    public CourseManageView(EntityManager em, StaffControllerFacade staffControllerFacade) {
        this.em = em;
        this.staffControllerFacade = staffControllerFacade;
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
            long id;

            //교수가 작성한 신청서 조회
            List<CourseCreateRequestDTO> courseApplications = staffControllerFacade.loadCourseApplicationList();
            if (courseApplications.isEmpty()) {
                System.out.println("강의 개설 신청이 없습니다.");
                return;
            }

            for (int i = 0; i < courseApplications.size(); i++) {
                CourseCreateRequestDTO ca = courseApplications.get(i);
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
            CourseCreateRequestDTO selected = courseApplications.get(index - 1);
            staffControllerFacade.createCourse(selected);
            System.out.println("강의 생성 요청을 반영하였습니다.");
        }
    }

    //개설된 강의 목록 확인
    public class CreatedCourseView {
        public void show() {

            System.out.println(" -- 개설된 강의 목록 -- ");

            List<CourseDTO> courseList = staffControllerFacade.loadCourseList();

            for (int i = 0; i < courseList.size(); i++) {
                CourseDTO courseDTO = courseList.get(i);

                System.out.printf("[%d]. " +
                        "강의명 : %s |" +
                        "교수명 : %s |" +
                        "강의실 : %s ", i + 1, courseDTO.getCourseName(), courseDTO.getProfessorName(), courseDTO.getClassroom());
                System.out.println();
            }
        }
    }

    public class CourseUpdateRequestView {

        public void show() {
            List<CourseUpdateRequest> requests = staffControllerFacade.getAllUpdateRequests();

            if (requests.isEmpty()) {
                System.out.println("수정 요청이 없습니다.");
                return;
            }

            System.out.println("=== 강의 수정 요청 목록 ===");
            for (int i = 0; i < requests.size(); i++) {
                CourseUpdateRequest req = requests.get(i);
                System.out.printf("[%d] 강의명: %s → %s / 학기: %s -> %s / 정원: %s -> %s / 강의실: %s -> %s / 강의 정보: %s -> %s / 사유: %s%n",
                        i,
                        req.getCourse().getCourseName(),
                        req.getCourseName(),
                        req.getCourse().getSemester(),
                        req.getSemester(),
                        req.getCourse().getCapacity(),
                        req.getCapacity(),
                        req.getCourse().getClassroom(),
                        req.getClassroom(),
                        req.getCourse().getContent(),
                        req.getContent(),

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
            staffControllerFacade.handleUpdateRequests(selectedRequest);
            System.out.println("강의 수정 요청을 반영하였습니다.");
        }
    }

    public class CourseDeleteRequestView {

        public void show() {
            List<CourseDeleteRequest> requests = staffControllerFacade.getAllDeleteRequests();

            if (requests.isEmpty()) {
                System.out.println("수정 요청이 없습니다.");
                return;
            }

            System.out.println("=== 강의 삭제 요청 목록 ===");
            for (int i = 0; i < requests.size(); i++) {
                CourseDeleteRequest req = requests.get(i);
                System.out.printf("[%d] 강의명: %s / 사유: %s%n",
                        i,
                        req.getCourse().getCourseName(),
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
            staffControllerFacade.processDeleteRequests(selectedRequest);
            System.out.println("강의 수정 요청을 반영하였습니다.");
        }
    }
}
