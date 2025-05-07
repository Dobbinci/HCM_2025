package com.be.view.staff.memberManage;

import com.be.service.Staff;

import java.util.Scanner;

public class MemberManagerView {
        public static void show(Staff staff) {
            MemberInfoView memberInfoView = new MemberInfoView();
            MemberInfoUpdateView memberInfoUpdateView = new MemberInfoUpdateView();

            while (true) { //로그아웃 시 home 로직이 종료될 수 있도록 수정
                System.out.println("메뉴");
                System.out.println("1. 사용자 전체 조회");
                System.out.println("2. 교수 조회");
                System.out.println("3. 학생 조회");
                System.out.println("4. 직원 조회");
                System.out.println("5. 사용자 정보 수정");
                System.out.println("6. 뒤로가기");

                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        memberInfoView.showAllMembers();
                        break;
                    case 2:
                        memberInfoView.showProfessor();
                        break;
                    case 3:
                        memberInfoView.showStudent();
                        break;
                    case 4:
                        memberInfoView.showStaff();
                        break;
                    case 5:
                        memberInfoUpdateView.show();
                        break;
                    case 6:
                        System.out.println("뒤로가기");
                        break;
                    default:
                        System.out.println("잘못된 메뉴입니다.");
                }
                if (choice == 6) break;
            }
        }
}
