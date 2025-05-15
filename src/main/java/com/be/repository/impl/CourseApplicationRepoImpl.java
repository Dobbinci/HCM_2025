package com.be.repository.impl;

import com.be.model.CourseApplication;
import com.be.model.Professor;
import com.be.repository.CourseApplicationRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CourseApplicationRepoImpl implements CourseApplicationRepository {

    private final EntityManager em;

    public CourseApplicationRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(CourseApplication courseApplication) {
        em.getTransaction().begin();
        em.persist(courseApplication);
        em.getTransaction().commit();
    }

    @Override
    public CourseApplication findById(Long id) {
        return em.find(CourseApplication.class, id);
    }

    @Override
    public List<CourseApplication> findAll() {
        return em.createQuery("SELECT c FROM CourseApplication c", CourseApplication.class).getResultList();
    }

    // 매번 같은 순서를 유지하기 위해 order by id 추가 (id로 정렬)
    public List<CourseApplication> findByProfessorId(Long professorId) {
        return em.createQuery("SELECT c FROM CourseApplication c WHERE c.professor.id = :professorId ORDER BY c.id", CourseApplication.class)
                .setParameter("professorId", professorId)
                .getResultList();
    }

    @Override
    public void update(CourseApplication courseApplication) {
        em.getTransaction().begin();
        em.merge(courseApplication);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        CourseApplication courseApplication = em.find(CourseApplication.class, id);
        if (courseApplication != null) {
            em.remove(courseApplication);
        }
        em.getTransaction().commit();
    }
}
