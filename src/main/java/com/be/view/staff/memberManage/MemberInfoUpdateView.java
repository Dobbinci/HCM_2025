package com.be.view.staff.memberManage;

import com.be.control.StaffController;
import com.be.service.Member;
import com.be.service.Professor;
import com.be.service.Staff;
import com.be.service.Student;

import java.util.Scanner;

public class MemberInfoUpdateView {
    private final StaffController controller = new StaffController();
    public void show() {

        if (controller.getAllMembers().isEmpty()) {
            System.out.println("수정할 사용자가 없습니다.");
            return;
        }

        System.out.println("=========== 전체 회원 정보 목록 ===========");
        System.out.printf("%-5s %-15s %-15s %-10s %-15s\n", "번호", "이름", "ID", "신분", "고유번호");
        System.out.println("--------------------------------------------------------------");

        for (int i = 0; i < controller.getAllMembers().size(); i++) {
            Member m = controller.getAllMembers().get(i);
            String name = getName(m);
            String uniqueId = getUniqueId(m);
            System.out.printf("%-5d %-15s %-15s %-10s %-15s\n",
                    i, name, m.getId(), m.getPosition(), uniqueId);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("수정할 사용자의 번호를 입력하세요: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (index < 0 || index >= controller.getAllMembers().size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }

        Member selected = controller.getAllMembers().get(index);
        System.out.println("새로운 정보를 입력하세요.");

        System.out.print("이름: ");
        String newName = scanner.nextLine();
        System.out.print("ID: ");
        String newId = scanner.nextLine();
        System.out.print("비밀번호: ");
        String newPw = scanner.nextLine();

        selected.setId(newId);
        selected.setPassword(newPw);

        if (selected instanceof Student student) {
            student.setName(newName);
        } else if (selected instanceof Professor professor) {
            professor.setName(newName);
        } else if (selected instanceof Staff s) {
            s.setName(newName);
        }

        System.out.println("사용자 정보가 성공적으로 수정되었습니다.");
    }

    private static String getName(Member m) {
        if (m instanceof Student student) {
            return student.getName();
        } else if (m instanceof Professor professor) {
            return professor.getName();
        } else if (m instanceof Staff staff) {
            return staff.getName();
        }
        return "";
    }

    private static String getUniqueId(Member m) {
        if (m instanceof Student student) {
            return student.getStudentId();
        } else if (m instanceof Professor professor) {
            return professor.getProfessorId();
        } else if (m instanceof Staff staff) {
            return staff.getStaffId();
        }
        return "";
    }
}
