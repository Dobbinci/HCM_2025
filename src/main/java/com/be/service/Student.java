package com.be.service;

public class Student extends Member{
    private String studentId;

    // professorId 필드용 getter/setter
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
