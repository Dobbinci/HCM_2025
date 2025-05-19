package com.be.repository;

import com.be.model.Course;
import com.be.model.CourseApplication;

import java.util.List;
import java.util.Map;

public interface CourseRepository {

    void save(Course course);

    Course findById(Long id);

    List<Course> findByProfessorId(Long professorId);

    List<Course> findAll();

    void update(Course course);

    void delete(Long id);
}
