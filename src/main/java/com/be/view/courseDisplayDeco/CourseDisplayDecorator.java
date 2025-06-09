package com.be.view.courseDisplayDeco;

import com.be.dto.CourseDTO;

import java.util.List;

public abstract class CourseDisplayDecorator implements CourseDisplayComponent {
    protected CourseDisplayComponent component;

    public CourseDisplayDecorator(CourseDisplayComponent component) {
        this.component = component;
    }

    public void displayCourse(List<CourseDTO> courseDTOS) {
        component.displayCourse(courseDTOS);
    }
}
