package com.be.view.staff;

import java.util.Scanner;

import com.be.controller.StaffControllerFacade;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StaffHomeView {


    private final EntityManager em;
    private final StaffControllerFacade staffControllerFacade;

    public void show() {
        Scanner scanner = new Scanner(System.in);

        String[] menuItems = {
                "1. 강의 관리",
                "2. 유저 관리",
                "3. 로그 아웃"
        };

        CourseManageView courseManageView = new CourseManageView(em, staffControllerFacade);
        MemberManageView memberManageView = new MemberManageView(em, staffControllerFacade);

        while (true) {
            System.out.println("메뉴");
            for (String items : menuItems) {
                System.out.println(items);
            }
            int choice = scanner.nextInt();

            if (choice == 1) {
                courseManageView.show();
            } else if (choice == 2) {
                memberManageView.show();
            } else {
                break;
            }
        }
    }
}

