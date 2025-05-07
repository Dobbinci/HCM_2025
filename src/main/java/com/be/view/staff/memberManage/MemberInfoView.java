package com.be.view.staff.memberManage;

import java.util.List;
import com.be.service.Member;
import com.be.service.Professor;
import com.be.service.Staff;
import com.be.service.Student;
import com.be.control.StaffController;

public class MemberInfoView {
    private final StaffController controller = new StaffController();

    public void showAllMembers() {
        List<Member> members = controller.getAllMembers();

        if (members.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
            return;
        }

        System.out.println("\n=========== 전체 회원 정보 목록 ===========");
        System.out.printf("%-15s %-15s %-15s %-15s%n", "이름", "ID", "신분", "고유번호");
        System.out.println("--------------------------------------------------------------");

        for (Member member : members) {
            String uniqueId = "";

            if (member instanceof Student student) {
                uniqueId = student.getStudentId();
            } else if (member instanceof Professor professor) {
                uniqueId = professor.getProfessorId();
            } else if (member instanceof Staff staff) {
                uniqueId = staff.getStaffId();
            }

            System.out.printf(
                "%-15s %-15s %-15s %-15s%n",
                member.getName(),
                member.getId(),
                member.getPosition(),
                uniqueId
            );
        }
    }

    public void showProfessor(){
        List<Member> members = controller.getProfessors();

        if (members.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
            return;
        }

        System.out.println("\n=========== 교수 회원 정보 목록 ===========");
        System.out.printf("%-15s %-15s %-15s %-15s%n", "이름", "ID", "신분", "고유번호");
        System.out.println("--------------------------------------------------------------");

        for (Member member : members) {
            String uniqueId = "";
            
            if (member instanceof Professor professor) {
                uniqueId = professor.getProfessorId();
            }

            System.out.printf(
                "%-15s %-15s %-15s %-15s%n",
                member.getName(),
                member.getId(),
                member.getPosition(),
                uniqueId
            );
        }
    }

    public void showStudent(){
        List<Member> members = controller.getStudnets();

        if (members.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
            return;
        }

        System.out.println("\n=========== 학생 회원 정보 목록 ===========");
        System.out.printf("%-15s %-15s %-15s %-15s%n", "이름", "ID", "신분", "고유번호");
        System.out.println("--------------------------------------------------------------");

        for (Member member : members) {
            String uniqueId = "";
            
            if (member instanceof Student student) {
                uniqueId = student.getStudentId();
            }

            System.out.printf(
                "%-15s %-15s %-15s %-15s%n",
                member.getName(),
                member.getId(),
                member.getPosition(),
                uniqueId
            );
        }
    }

    public void showStaff(){
        List<Member> members = controller.getStaffs();

        if (members.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
            return;
        }

        System.out.println("\n=========== 직원 회원 정보 목록 ===========");
        System.out.printf("%-15s %-15s %-15s %-15s%n", "이름", "ID", "신분", "고유번호");
        System.out.println("--------------------------------------------------------------");

        for (Member member : members) {
            String uniqueId = "";
            
            if (member instanceof Staff staff) {
                uniqueId = staff.getStaffId();
            }

            System.out.printf(
                "%-15s %-15s %-15s %-15s%n",
                member.getName(),
                member.getId(),
                member.getPosition(),
                uniqueId
            );
        }
    }
    
}
