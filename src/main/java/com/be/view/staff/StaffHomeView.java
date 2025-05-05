package com.be.view.staff;


import java.util.List;
import java.util.Scanner;

import com.be.control.StaffController;
import com.be.service.Member;
import com.be.service.Staff;
import com.be.view.professor.ProfessorMenuContext;
import com.be.view.professor.applicationViewStrategy.ApplicationViewStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StaffHomeView {
    public static void show(Staff staff) {
        String[] menuItems = {
                "1. 강의 생성",
                "2. 강의 개설 신청서 목록 확인",
                "3. 강의 수정",
                "4. 강의 삭제",
                "5. 유저 정보 관리",
                "6. 로그아웃"
        };

        Scanner scanner = new Scanner(System.in);

        while (true) { //로그아웃 시 home 로직이 종료될 수 있도록 수정
            System.out.println("메뉴");
            for (String items : menuItems) {
                System.out.println(items);
            }
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    CourseCreateView.show(staff);
                    break;
                case 2:
                    break;
                case 3:
                    CourseUpdateView.show(staff);
                    break;
                case 4:
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

