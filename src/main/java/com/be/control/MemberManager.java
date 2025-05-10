package com.be.control;

import com.be.service.Professor;
import com.be.service.Staff;
import com.be.service.Student;
import com.be.service.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberManager {

    private static final MemberManager instance = new MemberManager();

    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Professor> professorList = new ArrayList<>();
    private ArrayList<Staff> staffList = new ArrayList<>();

    private MemberManager(){

    }

    public static MemberManager getInstance(){
        return instance;
    }

    //로그인 검증하는 로직
    public Member manageLogin(String id, String pw) {
        // 교수 리스트에서 검색
        for (Professor professor : professorList) {
            if (professor.getId().equals(id) && professor.getPassword().equals(pw)) {
                return professor;
            }
        }
        // 학생 리스트에서 검색
        for (Student student : studentList) {
            if (student.getId().equals(id) && student.getPassword().equals(pw)) {
                return student;
            }
        }
        // 직원 리스트에서 검색
        for (Staff staff : staffList) {
            if (staff.getId().equals(id) && staff.getPassword().equals(pw)) {
                return staff;
            }
        }
        return null; // 로그인 실패
    }

    public boolean checkMemberRegistration(String userid, String position) {
        switch (position) {
            case "student" -> {
                for (Student student : studentList) {
                    if (student.getStudentId().equals(userid)) {
                        return true;
                    }
                }
            }
            case "professor" -> {
                for (Professor professor : professorList) {
                    if (professor.getProfessorId().equals(userid)) {
                        return true;
                    }
                }
            }
            case "staff" -> {
                for (Staff staff : staffList) {
                    if (staff.getStaffId().equals(userid)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean sameIdCheck(String id) {
        for (Professor professor : professorList) {
            if (professor.getId().equals(id)) {
                System.out.println("사용중인 ID입니다.");
                return true;
            }
        }
        // 학생 리스트에서 검색
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                System.out.println("사용중인 ID입니다.");
                return true;
            }
        }
        // 직원 리스트에서 검색
        for (Staff staff : staffList) {
            if (staff.getId().equals(id)) {
                System.out.println("사용중인 ID입니다.");
                return true;
            }
        }
        return false;
    }

    public void saveMember(String id, String pw, String name,String userid, String position ) {
        switch (position) {
            case "student" -> {
                Student s1 = new Student();
                s1.setId(id);
                s1.setPassword(pw);
                s1.setName(name);
                s1.setStudentId(userid);
                s1.setPosition(position);
                studentList.add(s1);
            }
            case "professor" -> {
                Professor p1 = new Professor();
                p1.setId(id);
                p1.setPassword(pw);
                p1.setName(name);
                p1.setProfessorId(userid);
                p1.setPosition(position);
                professorList.add(p1);
            }
            case "staff" -> {
                Staff s1 = new Staff();
                s1.setId(id);
                s1.setPassword(pw);
                s1.setName(name);
                s1.setStaffId(userid);
                s1.setPosition(position);
                staffList.add(s1);
            }
        }

    }
    public List<Member> getAllMembers() {
        List<Member> allMembers = new ArrayList<>();
        allMembers.addAll(studentList);
        allMembers.addAll(professorList);
        allMembers.addAll(staffList);
        return allMembers;
    }
    
    public List<Member> getProfessors() {
        List<Member> allMembers = new ArrayList<>();
        allMembers.addAll(professorList);
       
        return allMembers;
    }

    public List<Member> getStudents() {
        List<Member> allMembers = new ArrayList<>();
        allMembers.addAll(studentList);
       
        return allMembers;
    }

    public List<Member> getStaffs() {
        List<Member> allMembers = new ArrayList<>();
        allMembers.addAll(staffList);
       
        return allMembers;
    }
}
//싱글턴으로 바꿈