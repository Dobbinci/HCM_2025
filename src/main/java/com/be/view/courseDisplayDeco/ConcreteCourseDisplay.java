package com.be.view.courseDisplayDeco;

import com.be.dto.CourseDTO;

import java.util.List;

public class ConcreteCourseDisplay implements CourseDisplayComponent {

    @Override
    public void displayCourse(List<CourseDTO> courseDTOs) {
        System.out.println("개설 강의 목록");
    }
}
