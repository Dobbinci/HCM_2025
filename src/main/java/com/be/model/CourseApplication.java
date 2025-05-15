package com.be.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class CourseApplication {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String professorName;
    private String semester;
    private String credit;
    private String capacity;
    private String classroom;
    private String content;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    private Professor professor;

    protected CourseApplication() {
        // JPA requires a default constructor
    }

    public CourseApplication(String courseName, String professorName, String semester, String credit, String capacity, String classroom, String content) {
        this.courseName = courseName;
        this.professorName = professorName;
        this.semester = semester;
        this.credit = credit;
        this.capacity = capacity;
        this.classroom = classroom;
        this.content = content;
    }

}
