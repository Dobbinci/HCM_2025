package com.be.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
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

    public Course(String courseName, String professorName, String semester, String credit, String capacity, String classroom, String content) {
        this.courseName = courseName;
        this.professorName = professorName;
        this.semester = semester;
        this.credit = credit;
        this.capacity = capacity;
        this.classroom = classroom;
        this.content = content;
    }
}
