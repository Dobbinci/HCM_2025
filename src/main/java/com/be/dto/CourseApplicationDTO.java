package com.be.dto;

import com.be.model.Professor;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourseApplicationDTO {
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

    public CourseApplicationDTO() {
        // Default constructor
    }



}
