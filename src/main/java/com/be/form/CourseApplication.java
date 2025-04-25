package com.be.form;

public class CourseApplication {
    private String courseName;
    private String professorName;
    private String semester;
    private String credit;
    private String capacity;
    private String classroom;
    private String content;

    public CourseApplication(String courseName, String professorName, String semester, String credit, String capacity, String classroom, String content) {
        this.courseName = courseName;
        this.professorName = professorName;
        this.semester = semester;
        this.credit = credit;
        this.capacity = capacity;
        this.classroom = classroom;
        this.content = content;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
