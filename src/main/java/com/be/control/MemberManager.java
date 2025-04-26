package com.be.control;

import com.be.service.Professor;
import com.be.service.Staff;
import com.be.service.Student;
import com.be.view.LoginView;
import com.be.view.student.StudentView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MemberManager {

    private static ArrayList<Student> studentList = new ArrayList<>();
    private static ArrayList<Professor> professorList = new ArrayList<>();
    private static ArrayList<Staff> staffList = new ArrayList<>();

    //로그인 검증하는 로직
    public Professor manageLogin(String id, String pw){//입력받은 아이디와 비밀번호 받는다.
        for (Professor professor : professorList) {
            if (professor.getId().equals(id) && professor.getPassword().equals(pw)) {
                return professor; // 로그인 성공한 교수 객체 리턴
            }
        }
        return null; // 로그인 실패
    }


    //로그인 기능해보려고 만든 더미 데이터터
    public void initDummyData() {
        Professor p1 = new Professor();
        p1.setId("prof1");
        p1.setPassword("1234");
        p1.setName("홍길동");
        p1.setProfessorId("P001");
        professorList.add(p1);

        Professor p2 = new Professor();
        p2.setId("prof2");
        p2.setPassword("5678");
        p2.setName("김철수");
        p2.setProfessorId("P002");
        professorList.add(p2);

        Professor p3 = new Professor();
        p3.setId("prof3");
        p3.setPassword("abcd");
        p3.setName("이영희");
        p3.setProfessorId("P003");
        professorList.add(p3);
    }
}
