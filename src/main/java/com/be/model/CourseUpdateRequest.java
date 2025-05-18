package com.be.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseUpdateRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Professor professor;

    // 수정할 내용
    private String courseName;
    private String semester;
    private String credit;
    private String capacity;
    private String classroom;
    private String content;

    private String reason; // 수정 요청 사유
}
