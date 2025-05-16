package com.be.view.staff;

import java.util.Scanner;

import com.be.model.Staff;
import jakarta.persistence.EntityManager;

public class StaffHomeView {

    Scanner scanner = new Scanner(System.in);
    private final EntityManager em;

    public StaffHomeView(EntityManager em) {
        this.em = em;
    }

    public void show(Staff staff) {
        String[] menuItems = {
                "1. 강의 관리",
                "2. 유저 관리",
                "3. 로그 아웃"
        };

        CourseManageView courseManageView = new CourseManageView(em);
        MemberManageView memberManageView = new MemberManageView(em);

        while (true) {
            System.out.println("메뉴");
            for (String items : menuItems) {
                System.out.println(items);
            }
            int choice = scanner.nextInt();
            if (choice == 1) {
                courseManageView.show(staff);
            } else if (choice == 2) {
                memberManageView.show(staff);
            } else {
                break;
            }
        }
    }
}

