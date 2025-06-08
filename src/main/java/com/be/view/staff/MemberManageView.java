package com.be.view.staff;
import com.be.controller.StaffControllerFacade;
import com.be.model.Member;
import com.be.model.Professor;
import com.be.model.Staff;
import com.be.model.Student;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class MemberManageView {

    private final EntityManager em;
    private final StaffControllerFacade staffController;

    public void show() {
        MemberInfoInterface memberInfoView = new ProxyMemberInfoView(new MemberInfoView());

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

    public class MemberInfoView implements MemberInfoInterface{

        public void showAllMembers() {
            Scanner scanner = new Scanner(System.in);
            List<Member> members = staffController.getAllMembers();
            List<Member> hanmadiEligibleList = new ArrayList<>();

            if (members.isEmpty()) {
                System.out.println("등록된 멤버가 없습니다.");
                return;
            }

            MemberVisitor visitor = new ConcreteVisitor();

            System.out.println("\n=========== 전체 회원 정보 목록 ===========");
            System.out.printf("%-15s %-15s %-15s%n", "이름", "ID", "신분");
            System.out.println("------------------------------------------");

            for (Member member : members) {
                System.out.printf(
                        "%-15s %-15s %-15s%n",
                        member.getName(),
                        member.getSystemId(),
                        member.getPosition()
                );
                if (member instanceof Student || member instanceof Professor) {
                    hanmadiEligibleList.add(member);
                }
            }
            if (!hanmadiEligibleList.isEmpty()) {
                System.out.print("\n학생/교수의 한마디를 몰래 보시겠습니까? (Y/N): ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("Y")) {
                    System.out.println("\n===== 대상 목록 =====");
                    for (int i = 0; i < hanmadiEligibleList.size(); i++) {
                        Member member = hanmadiEligibleList.get(i);
                        System.out.printf("%d. %s ( %s )%n", i + 1, member.getName(), member.getPosition());
                    }

                    System.out.print("확인할 번호 입력: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // 개행 처리

                    if (choice >= 1 && choice <= hanmadiEligibleList.size()) {
                        Member selected = hanmadiEligibleList.get(choice - 1);
                        if (selected instanceof Student) {
                            System.out.println("\n[" + selected.getName() + "]의 한마디: " + selected.getHanmadi());
                        } else if (selected instanceof Professor) {
                            System.out.println("\n[" + selected.getName() + "]의 한마디: " + selected.getHanmadi());
                        }
                    } else {
                        System.out.println("잘못된 번호입니다.");
                    }
                }
            } else {
                System.out.println("\n학생 또는 교수 정보가 없어 한마디를 확인할 수 없습니다.");
            }
        }

        public void showProfessor() {
            List<Professor> professors = staffController.getProfessors();

            if (professors.isEmpty()) {
                System.out.println("등록된 멤버가 없습니다.");
                return;
            }

            System.out.println("\n=========== 교수 회원 정보 목록 ===========");
            System.out.printf("%-15s %-15s %-15s%n", "이름", "ID", "신분");
            System.out.println("-------------------------------------------");

            for (Professor professor : professors) {
                System.out.printf(
                        "%-15s %-15s %-15s %n",
                        professor.getName(),
                        professor.getSystemId(),
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
            System.out.printf("%-15s %-15s %-15s %n", "이름", "ID", "신분");
            System.out.println("------------------------------------------");

            for (Member member : members) {
                System.out.printf(
                        "%-15s %-15s %-15s %n",
                        member.getName(),
                        member.getSystemId(),
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
            System.out.printf("%-15s %-15s %-15s%n", "이름", "ID", "신분");
            System.out.println("------------------------------------------");

            for (Member member : members) {
                System.out.printf(
                        "%-15s %-15s %-15s %n",
                        member.getName(),
                        member.getSystemId(),
                        member.getPosition()
                );
            }
        }
    }

    public class ProxyMemberInfoView implements MemberInfoInterface {
        private final MemberInfoView realView;
        private final Scanner scanner;

        public ProxyMemberInfoView(MemberInfoView realView) {
            this.realView = realView;
            this.scanner = new Scanner(System.in);
        }

        private boolean authenticate() {
            System.out.print("인증 번호를 입력하세요: ");
            String input = scanner.nextLine();
            return "staff1234".equals(input);  // 인증 번호 = staff1234
        }

        @Override
        public void showAllMembers() {
            if (authenticate()) realView.showAllMembers();
            else System.out.println("인증 실패: 접근이 거부되었습니다.");
        }

        @Override
        public void showProfessor() {
            if (authenticate()) realView.showProfessor();
            else System.out.println("인증 실패: 접근이 거부되었습니다.");
        }

        @Override
        public void showStudent() {
            if (authenticate()) realView.showStudent();
            else System.out.println("인증 실패: 접근이 거부되었습니다.");
        }

        @Override
        public void showStaff() {
            if (authenticate()) realView.showStaff();
            else System.out.println("인증 실패: 접근이 거부되었습니다.");
        }
    }

}
