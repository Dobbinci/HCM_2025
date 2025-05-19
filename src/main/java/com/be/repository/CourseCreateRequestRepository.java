package com.be.repository;

import com.be.model.CourseCreateRequest;

import java.util.List;

public interface CourseCreateRequestRepository {
    // Define methods for CRUD operations on CourseApplication entities
    void save(CourseCreateRequest courseCreateRequest);

    CourseCreateRequest findById(Long id);

    List<CourseCreateRequest> findByProfessorId(Long professorId);

    List<CourseCreateRequest> findAll();

    void update(CourseCreateRequest courseCreateRequest);

    void delete(Long id);
}
