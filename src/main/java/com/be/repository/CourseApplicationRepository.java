package com.be.repository;

import com.be.model.CourseApplication;
import com.be.model.Professor;

import java.util.List;

public interface CourseApplicationRepository {
    // Define methods for CRUD operations on CourseApplication entities
    void save(CourseApplication courseApplication);

    CourseApplication findById(Long id);

    List<CourseApplication> findByProfessorId(Long professorId);

    List<CourseApplication> findAll();

    void update(CourseApplication courseApplication);

    void delete(Long id);
}
