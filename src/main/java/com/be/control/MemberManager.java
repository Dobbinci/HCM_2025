package com.be.control;

import com.be.service.Professor;
import com.be.service.Staff;
import com.be.service.Student;
import com.be.service.Member;
import com.be.view.LoginView;
import com.be.view.student.StudentView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MemberManager {

    private static ArrayList<Student> studentList = new ArrayList<>();
    private static ArrayList<Professor> professorList = new ArrayList<>();
    private static ArrayList<Staff> staffList = new ArrayList<>();

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

    public boolean checkUserRegistration(String userid, String position) {
        switch (position) {
            case "student" -> {
                for (Student student : studentList) {
                    if (student.getId().equals(userid)) {
                        System.out.println("중복된 아이디입니다");
                        return true;
                    }
                }
            }
            case "professor" -> {
                for (Professor professor : professorList) {
                    if (professor.getId().equals(userid)) {
                        System.out.println("중복된 아이디입니다");
                        return true;
                    }
                }
            }
            case "staff" -> {
                for (Staff staff : staffList) {
                    if (staff.getId().equals(userid)) {
                        System.out.println("중복된 아이디입니다");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean sameidcheck(String id) {
        for (Professor professor : professorList) {
            if (professor.getId().equals(id)) {
                return true;
            }
        }
        // 학생 리스트에서 검색
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                return true;
            }
        }
        // 직원 리스트에서 검색
        for (Staff staff : staffList) {
            if (staff.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void saveUser(String id, String pw, String name,String userid, String position ) {
        switch (position) {
            case "student" -> {
                Student s1 = new Student();
                s1.setId(id);
                s1.setPassword(pw);
                s1.setName(name);
                s1.setStudentId(userid);
                studentList.add(s1);
            }
            case "professor" -> {
                Professor p1 = new Professor();
                p1.setId(id);
                p1.setPassword(pw);
                p1.setName(name);
                p1.setProfessorId(userid);
                professorList.add(p1);
            }
            case "staff" -> {
                Staff s1 = new Staff();
                s1.setId(id);
                s1.setPassword(pw);
                s1.setName(name);
                s1.setStaffId(userid);
                staffList.add(s1);
            }
        }

    }
}
