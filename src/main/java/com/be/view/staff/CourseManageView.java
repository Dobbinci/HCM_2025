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

    public void show(Staff staff) {
        String[] menuItems = {
                "1. 강의 생성",
                "2. 강의 수정",
                "3. 강의 삭제",
                "4. 전체 강의 조회",
                "5. 로그아웃"
        };

        while (true) {
            System.out.println("메뉴");
            for (String items : menuItems) {
                System.out.println(items);
            }
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    CourseCreateView courseCreateView = new CourseCreateView();
                    courseCreateView.show(staff);
                    break;
                case 2:
                    CourseUpdateView courseUpdateView = new CourseUpdateView();
                    courseUpdateView.show(staff);
                    break;
                case 3:
                    // 삭제 로직
                    break;
                case 4:
                    CreatedCourseView createdCourseView = new CreatedCourseView();
                    createdCourseView.show(staff);
                    break;
            }
            if (choice == 5) {
                break;
            }
        }
    }

    //강의 생성 화면
    public class CourseCreateView {
        public void show(Staff staff) {
            long id;

            //교수가 작성한 신청서 조회
            List<CourseApplication> courseApplications = staffController.getCourseApplications();
            for (CourseApplication courseApplication : courseApplications) {
                System.out.println(courseApplication.getId());
                System.out.println(courseApplication.getCourseName());
                System.out.println(courseApplication.getProfessor());
                System.out.println(courseApplication.getClassroom());
            }

            System.out.println("생성할 강의 번호 선택 : ");
            id = scanner.nextLong();
            staffController.createCourse(id);
        }
    }

    //강의 수정 화면
    public class CourseUpdateView {
        public void show(Staff staff) {
            long id;
            String newCourseName;
            String newProfessorName;
            String newSemester;
            String newCredit;
            String newCapacity;
            String newClassroom;
            String newContent;

            //개설된 강의 조회
            CourseManageView courseManageView = new CourseManageView(em);
            CourseManageView.CourseCreateView view = courseManageView.new CourseCreateView();

            System.out.println("수정할 강의 번호 선택 : ");
            id = scanner.nextLong();
            Course course = staffController.getCourse(id);
            System.out.println("과목명 : " + course.getCourseName());
            System.out.println("교수명 : " + course.getProfessorName());
            System.out.println("학기 : " + course.getSemester());
            System.out.println("학점 : " + course.getCredit());
            System.out.println("정원 : " + course.getCapacity());
            System.out.println("강의실 : " + course.getClassroom());
            System.out.println("내용 : " + course.getContent());


            //수정값 입력
            System.out.println("과목명 수정 : ");
            newCourseName = scanner.nextLine();

            System.out.println("교수명 수정 : ");
            newProfessorName = scanner.nextLine();

            System.out.println("학기 수정 : ");
            newSemester = scanner.nextLine();

            System.out.println("학점 수정 : ");
            newCredit = scanner.nextLine();

            System.out.println("정원 수정 : ");
            newCapacity = scanner.nextLine();

            System.out.println("강의실 수정 : ");
            newClassroom = scanner.nextLine();

            System.out.println("내용 수정 : ");
            newContent = scanner.nextLine();

            staffController.updateCourse(id, newCourseName, newProfessorName, newSemester, newCredit, newCapacity, newClassroom, newContent);
        }
    }

    //개설된 강의 목록 화면
    public class CreatedCourseView {
        public void show(Member member) {

            System.out.println(" -- 개설된 강의 목록 -- ");
            List<Course> courseList = staffController.getCreatedCourse();
            for (Course course : courseList) {
                System.out.println(course.getId());
                System.out.println(course.getCourseName());
                System.out.println(course.getProfessor());
                System.out.println(course.getClassroom());
            }
        }
    }

    public class CourseUpdateRequestView {

        public void show(Staff staff) {
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

        public void show(Staff staff) {
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
            //staffController.processDeleteRequests(index);
            System.out.println("강의 수정 요청을 반영하였습니다.");
        }
    }
}
