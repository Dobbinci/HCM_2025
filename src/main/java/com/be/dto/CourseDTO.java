package com.be.dto;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourseDTO {
    private Long id;
    private String courseName;
    private String professorName;
    private String semester;
    private String credit;
    private String capacity;
    private String classroom;
    private String content;
    private Long professorId;
}
