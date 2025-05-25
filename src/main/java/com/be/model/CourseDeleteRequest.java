package com.be.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDeleteRequest {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    private String professorName;

    private String reason; // 삭제 사유 (선택사항)
    @Builder.Default
    private boolean handled = false;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    private Professor professor;

    @ManyToOne
    private Course course;

}
