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

        Student s1 = new Student();
        s1.setId("student1");
        s1.setPassword("1111");
        s1.setName("박민수");
        s1.setStudentId("S001");
        studentList.add(s1);

        Student s2 = new Student();
        s2.setId("student2");
        s2.setPassword("2222");
        s2.setName("최지우");
        s2.setStudentId("S002");
        studentList.add(s2);

        Student s3 = new Student();
        s3.setId("student3");
        s3.setPassword("3333");
        s3.setName("장동건");
        s3.setStudentId("S003");
        studentList.add(s3);

        // ===== 직원 더미 =====
        Staff st1 = new Staff();
        st1.setId("staff1");
        st1.setPassword("aaaa");
        st1.setName("이승기");
        staffList.add(st1);

        Staff st2 = new Staff();
        st2.setId("staff2");
        st2.setPassword("bbbb");
        st2.setName("김하늘");
        staffList.add(st2);

        Staff st3 = new Staff();
        st3.setId("staff3");
        st3.setPassword("cccc");
        st3.setName("정우성");
        staffList.add(st3);
    }
}
