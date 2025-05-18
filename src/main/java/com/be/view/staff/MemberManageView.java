package com.be.view.staff;
import com.be.controller.StaffController;
import com.be.model.Member;
import com.be.model.Professor;
import com.be.model.Staff;
import com.be.model.Student;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class MemberManageView {

    private final EntityManager em;
    private final StaffController staffController;

    public MemberManageView(EntityManager em){
        this.em = em;
        this.staffController = new StaffController(em);
    }

    public void show(Staff staff) {
        MemberInfoView memberInfoView = new MemberInfoView();
//        MemberInfoUpdateView memberInfoUpdateView = new MemberInfoUpdateView();

        while (true) {
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
//                    memberInfoUpdateView.show();
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

//    public class MemberInfoUpdateView {
//        public void show() {
//
//            if (staffController.getAllMembers().isEmpty()) {
//                System.out.println("수정할 사용자가 없습니다.");
//                return;
//            }
//
//            System.out.println("=========== 전체 회원 정보 목록 ===========");
//            System.out.printf("%-5s %-15s %-15s %-10s %-15s\n", "번호", "이름", "ID", "신분", "고유번호");
//            System.out.println("--------------------------------------------------------------");
//
//            for (int i = 0; i < controller.getAllMembers().size(); i++) {
//                Member m = controller.getAllMembers().get(i);
//                String name = getName(m);
//                String uniqueId = getUniqueId(m);
//                System.out.printf("%-5d %-15s %-15s %-10s %-15s\n",
//                        i, name, m.getId(), m.getPosition(), uniqueId);
//            }
//
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("수정할 사용자의 번호를 입력하세요: ");
//            int index = scanner.nextInt();
//            scanner.nextLine(); // consume newline
//
//            if (index < 0 || index >= controller.getAllMembers().size()) {
//                System.out.println("잘못된 번호입니다.");
//                return;
//            }
//
//            Member selected = controller.getAllMembers().get(index);
//            System.out.println("새로운 정보를 입력하세요.");
//
//            System.out.print("이름: ");
//            String newName = scanner.nextLine();
//            System.out.print("ID: ");
//            String newId = scanner.nextLine();
//            System.out.print("비밀번호: ");
//            String newPw = scanner.nextLine();
//
//            selected.setId(newId);
//            selected.setPassword(newPw);
//
//            if (selected instanceof Student student) {
//                student.setName(newName);
//            } else if (selected instanceof Professor professor) {
//                professor.setName(newName);
//            } else if (selected instanceof Staff s) {
//                s.setName(newName);
//            }
//
//            System.out.println("사용자 정보가 성공적으로 수정되었습니다.");
//        }
//
//        private static String getName(Member m) {
//            if (m instanceof Student student) {
//                return student.getName();
//            } else if (m instanceof Professor professor) {
//                return professor.getName();
//            } else if (m instanceof Staff staff) {
//                return staff.getName();
//            }
//            return "";
//        }
//
//        private static String getUniqueId(Member m) {
//            if (m instanceof Student student) {
//                return student.getStudentId();
//            } else if (m instanceof Professor professor) {
////                return professor.getProfessorId();
//            } else if (m instanceof Staff staff) {
//                return staff.getStaffId();
//            }
//            return "";
//        }
//    }

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
//                String uniqueId = "";

//                if (member instanceof Student student) {
//                    uniqueId = student.getStudentId();
//                } else if (member instanceof Professor professor) {
//                    uniqueId = professor.getProfessorId(); -> 삭제 예상
//                } else if (member instanceof Staff staff) {
//                    uniqueId = staff.getStaffId();
//                }

                System.out.printf(
                        "%-15s %-15s %-15s%n",
                        member.getName(),
                        member.getId(),
                        member.getPosition()
//                        uniqueId
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
//                String uniqueId = "";
//                if (member instanceof Professor professor) {
//                uniqueId = professor.getProfessorId();
//                }
                System.out.printf(
                        "%-15s %-15s %-15s %n",
                        professor.getName(),
                        professor.getId(),
                        professor.getPosition()
//                        uniqueId
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
//                String uniqueId = "";
//
//                if (member instanceof Staff staff) {
//                    uniqueId = staff.getStaffId();
//                }

                System.out.printf(
                        "%-15s %-15s %-15s %n",
                        member.getName(),
                        member.getId(),
                        member.getPosition()
//                        uniqueId
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
//                String uniqueId = "";
//
//                if (member instanceof Student student) {
//                    uniqueId = student.getStudentId();
//                }

                System.out.printf(
                        "%-15s %-15s %-15s %n",
                        member.getName(),
                        member.getId(),
                        member.getPosition()
//                        uniqueId
                );
            }
        }
    }
}
