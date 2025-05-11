package com.be.model;

import com.be.service.Professor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Entity
@Builder
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

    public Course() {

    }
}
