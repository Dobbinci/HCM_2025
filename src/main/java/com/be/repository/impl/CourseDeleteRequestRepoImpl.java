package com.be.repository.impl;

import com.be.model.CourseDeleteRequest;
import com.be.repository.CourseDeleteRequestRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CourseDeleteRequestRepoImpl implements CourseDeleteRequestRepository {

    private final EntityManager em;

    public CourseDeleteRequestRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(CourseDeleteRequest request) {
        em.getTransaction().begin();
        em.persist(request);
        em.getTransaction().commit();
    }

    @Override
    public List<CourseDeleteRequest> findAll() {
        return em.createQuery("SELECT r FROM CourseDeleteRequest r", CourseDeleteRequest.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        em.getTransaction().begin();
        CourseDeleteRequest request = em.find(CourseDeleteRequest.class, id);
        if (request != null) {
            em.remove(request);
        }
        em.getTransaction().commit();
    }
}
