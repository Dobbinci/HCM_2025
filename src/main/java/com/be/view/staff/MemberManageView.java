package com.be.view.staff;
import com.be.controller.StaffController;
import com.be.model.Member;
import com.be.model.Professor;
import com.be.model.Staff;
import com.be.model.Student;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class MemberManageView {

    private final EntityManager em;
    private final StaffController staffController;

    public void show() {
        MemberInfoView memberInfoView = new MemberInfoView();

        while (true) {
            System.out.println("메뉴");
            System.out.println("1. 사용자 전체 조회");
            System.out.println("2. 교수 조회");
            System.out.println("3. 학생 조회");
            System.out.println("4. 직원 조회");
            System.out.println("5. 뒤로가기");

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
                default:
                    System.out.println("잘못된 메뉴입니다.");
            }
            if (choice == 5) break;
        }
    }

    public class MemberInfoView {

        public void showAllMembers() {
            List<Member> members = staffController.getAllMembers();

            if (members.isEmpty()) {
                System.out.println("등록된 멤버가 없습니다.");
                return;
            }

            System.out.println("\n=========== 전체 회원 정보 목록 ===========");
            System.out.printf("%-15s %-15s %-15s %-15s%n", "이름", "ID", "신분", "고유번호");
            System.out.println("--------------------------------------------------------------");

            for (Member member : members) {
                System.out.printf(
                        "%-15s %-15s %-15s%n",
                        member.getName(),
                        member.getId(),
                        member.getPosition()
                );
            }
        }

        public void showProfessor() {
            List<Professor> professors = staffController.getProfessors();

            if (professors.isEmpty()) {
                System.out.println("등록된 멤버가 없습니다.");
                return;
            }

            System.out.println("\n=========== 교수 회원 정보 목록 ===========");
            System.out.printf("%-15s %-15s %-15s %-15s%n", "이름", "ID", "신분", "고유번호");
            System.out.println("--------------------------------------------------------------");

            for (Professor professor : professors) {
                System.out.printf(
                        "%-15s %-15s %-15s %n",
                        professor.getName(),
                        professor.getId(),
                        professor.getPosition()
                );
            }
        }

        public void showStaff() {
            List<Staff> members = staffController.getStaffs();

            if (members.isEmpty()) {
                System.out.println("등록된 멤버가 없습니다.");
                return;
            }

            System.out.println("\n=========== 직원 회원 정보 목록 ===========");
            System.out.printf("%-15s %-15s %-15s %-15s%n", "이름", "ID", "신분", "고유번호");
            System.out.println("--------------------------------------------------------------");

            for (Member member : members) {
                System.out.printf(
                        "%-15s %-15s %-15s %n",
                        member.getName(),
                        member.getId(),
                        member.getPosition()
                );
            }
        }

        public void showStudent() {
            List<Student> members = staffController.getStudents();

            if (members.isEmpty()) {
                System.out.println("등록된 멤버가 없습니다.");
                return;
            }

            System.out.println("\n=========== 학생 회원 정보 목록 ===========");
            System.out.printf("%-15s %-15s %-15s %-15s%n", "이름", "ID", "신분", "고유번호");
            System.out.println("--------------------------------------------------------------");

            for (Member member : members) {
                System.out.printf(
                        "%-15s %-15s %-15s %n",
                        member.getName(),
                        member.getId(),
                        member.getPosition()
                );
            }
        }
    }
}
