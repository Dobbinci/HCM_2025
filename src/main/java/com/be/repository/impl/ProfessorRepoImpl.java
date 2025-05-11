package com.be.repository.impl;

import com.be.model.Professor;
import com.be.repository.ProfessorRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProfessorRepoImpl implements ProfessorRepository {

    private final EntityManager em;

    public ProfessorRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Professor professor) {
        em.getTransaction().begin();
        em.persist(professor);
        em.getTransaction().commit();
    }

    @Override
    public List<Professor> findAll() {
        return em.createQuery("SELECT p FROM Professor p", Professor.class)
                .getResultList();
    }

    @Override
    public Professor findById(Long id) {
        return em.find(Professor.class, id);
    }

    @Override
    public void update(Professor professor) {
        em.getTransaction().begin();
        em.merge(professor);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Professor professor) {
        em.getTransaction().begin();
        em.remove(professor);
        em.getTransaction().commit();
    }


}
