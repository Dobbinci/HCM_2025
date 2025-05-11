package com.be.view.staff;


import java.util.Scanner;

import com.be.model.Staff;
import com.be.view.staff.courseManage.CourseCreateView;
import com.be.view.staff.courseManage.CourseUpdateView;
import com.be.view.staff.courseManage.CreatedCourseView;
import com.be.view.staff.memberManage.MemberManagerView;
import jakarta.persistence.EntityManager;

public class StaffHomeView {
    private final EntityManager em;

    public StaffHomeView(EntityManager em) {
        this.em = em;
    }

    public void show(Staff staff) {
        String[] menuItems = {
                "1. 강의 생성",
                "2. 강의 수정",
                "3. 강의 삭제",
                "4. 전체 강의 조회",
                "5. 유저 정보 관리",
                "6. 로그아웃"
        };

        Scanner scanner = new Scanner(System.in);
        CreatedCourseView createdCourseView = new CreatedCourseView(em);
        StaffCourseApplicationViewStrategy staffCourseApplicationViewStrategy = new StaffCourseApplicationViewStrategy();

        while (true) { //로그아웃 시 home 로직이 종료될 수 있도록 수정
            System.out.println("메뉴");
            for (String items : menuItems) {
                System.out.println(items);
            }
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    CourseCreateView courseCreateView = new CourseCreateView(em);
                    courseCreateView.show(staff);
                    break;
                case 2:
                    CourseUpdateView.show(staff);
                    break;
                case 3:
                    // 삭제 로직
                    break;
                case 4:
                    createdCourseView.show(staff);
                    break;
                case 5:
                    MemberManagerView.show(staff);
                    break;
            }
            if (choice == 6) {
                break;
            }
        }
    }
}

