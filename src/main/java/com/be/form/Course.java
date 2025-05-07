package com.be.form;

public class Course {
    private String name;
    private String professorName;
//    private int major;
//    private int code;
//    private int year;
//    private int semester;
//    private int time;

    public Course(String name, String professorName){
//           int major, int code, int year, int semester, int time) {
        this.name = name;
        this.professorName = professorName;
//        this.major = major;
//        this.code = code;
//        this.year = year;
//        this.semester = semester;
//        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

//    public int getMajor() {
//        return major;
//    }
//
//    public void setMajor(int major) {
//        this.major = major;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }
//
//    public int getSemester() {
//        return semester;
//    }
//
//    public void setSemester(int semester) {
//        this.semester = semester;
//    }
//
//    public int getTime() {
//        return time;
//    }
//
//    public void setTime(int time) {
//        this.time = time;
//    }
}
