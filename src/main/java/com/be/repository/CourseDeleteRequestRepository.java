package com.be.repository;

import com.be.model.CourseDeleteRequest;
import java.util.List;

public interface CourseDeleteRequestRepository {
    void save(CourseDeleteRequest request);
    List<CourseDeleteRequest> findAll();
    void deleteById(Long id);
}
