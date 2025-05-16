package com.be.repository.impl;

import com.be.model.CourseApplication;
import com.be.model.CourseUpdateRequest;
import com.be.repository.CourseUpdateRequestRepository;
import jakarta.persistence.EntityManager;
import java.util.List;

public class CourseUpdateRequestRepoImpl implements CourseUpdateRequestRepository {
    private final EntityManager em;

    public CourseUpdateRequestRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(CourseUpdateRequest request) {
        em.getTransaction().begin();
        em.persist(request);
        em.getTransaction().commit();
    }

    @Override
    public List<CourseUpdateRequest> findAll() {
        return em.createQuery("SELECT r FROM CourseUpdateRequest r", CourseUpdateRequest.class).getResultList();
    }

    @Override
    public void delete(CourseUpdateRequest request) {
        em.getTransaction().begin();
        em.remove(em.contains(request) ? request : em.merge(request));
        em.getTransaction().commit();
    }
}
