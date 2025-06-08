package com.be.view.staff;

import com.be.model.Professor;
import com.be.model.Staff;
import com.be.model.Student;

public class ConcreteVisitor implements MemberVisitor {

    @Override
    public void visit(Professor professor) {
        System.out.println(professor.getName() + " 교수님의 한마디: " + professor.getHanmadi());
    }

    @Override
    public void visit(Student student) {
        System.out.println(student.getName() + " 학생의 한마디: " + student.getHanmadi());
    }

    @Override
    public void visit(Staff staff) {
        System.out.println(staff.getName()+ " 스태프입니다. 전화번호: " + staff.getHanmadi());
    }
}
