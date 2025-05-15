package com.be.repository;

import com.be.model.CourseUpdateRequest;

import java.util.List;

public interface CourseUpdateRequestRepository {
    List<CourseUpdateRequest> findAll();
    void save(CourseUpdateRequest request);
    void delete(CourseUpdateRequest request);

}
