package com.be.repository.impl;

import com.be.model.CourseCreateRequest;
import com.be.repository.CourseCreateRequestRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CourseCreateRequestRepoImpl implements CourseCreateRequestRepository {

    private final EntityManager em;

    public CourseCreateRequestRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(CourseCreateRequest courseCreateRequest) {
        em.getTransaction().begin();
        em.persist(courseCreateRequest);
        em.getTransaction().commit();
    }

    @Override
    public CourseCreateRequest findById(Long id) {
        return em.find(CourseCreateRequest.class, id);
    }

    @Override
    public List<CourseCreateRequest> findAll() {
        return em.createQuery("SELECT c FROM CourseCreateRequest c", CourseCreateRequest.class).getResultList();
    }

    // 매번 같은 순서를 유지하기 위해 order by id 추가 (id로 정렬)
    public List<CourseCreateRequest> findByProfessorId(Long professorId) {
        return em.createQuery("SELECT c FROM CourseCreateRequest c WHERE c.professor.id = :professorId ORDER BY c.id", CourseCreateRequest.class)
                .setParameter("professorId", professorId)
                .getResultList();
    }

    @Override
    public void update(CourseCreateRequest courseCreateRequest) {
        em.getTransaction().begin();
        em.merge(courseCreateRequest);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        CourseCreateRequest courseCreateRequest = em.find(CourseCreateRequest.class, id);
        if (courseCreateRequest != null) {
            em.remove(courseCreateRequest);
        }
        em.getTransaction().commit();
    }
}
