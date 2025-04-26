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


    public Professor manageLogin(String id, String pw){
       
        boolean loginSuccess = false;
        Professor loggedInProfessor = null;

        while(!loginSuccess){
            for (Professor professor : professorList) {
                if (professor.getId().equals(id) && professor.getPassword().equals(pw)) {
                    System.out.println("로그인 성공! 환영합니다, " + professor.getName() + " 교수님.");
                    loginSuccess = true;
                    loggedInProfessor = professor;
                    break;
                }
            }
        }
        return loggedInProfessor;
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
