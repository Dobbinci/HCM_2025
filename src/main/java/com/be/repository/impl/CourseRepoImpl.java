package com.be.repository.impl;

import com.be.model.Course;
import com.be.repository.CourseRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CourseRepoImpl implements CourseRepository {

    private final EntityManager em;

    public CourseRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Course course) {
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
    }

    @Override
    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public List<Course> findByProfessorId(Long professorId) {
        return em.createQuery("SELECT c FROM Course c WHERE c.professor.id = :professorId ORDER BY c.id", Course.class)
                .setParameter("professorId", professorId)
                .getResultList();
    }

    @Override
    public List<Course> findAll() {
        return em.createQuery("SELECT c FROM Course c ORDER BY c.id", Course.class).getResultList();
    }

    @Override
    public void update(Course course) {
        em.getTransaction().begin();
        em.merge(course);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        Course course = em.find(Course.class, id);
        if (course != null) {
            em.remove(course);
        }
        em.getTransaction().commit();
    }
}
