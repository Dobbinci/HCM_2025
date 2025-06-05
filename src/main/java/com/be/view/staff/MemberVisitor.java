package com.be.view.staff;

import com.be.model.Professor;
import com.be.model.Staff;
import com.be.model.Student;

public interface MemberVisitor {
    void visit(Professor professor);
    void visit(Student student);
    void visit(Staff staff);
}
