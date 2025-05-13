package com.be.repository.impl;

import com.be.repository.GenericRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GenericRepoImpl<T, ID> implements GenericRepository<T, ID> {
    protected final EntityManager em;

    // 런타임에 제네릭은 타입이 사라지는데 타입이 유지되게 하기 위해 선언
    private final Class<T> entityClass;

    public GenericRepoImpl(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public List<T> findAll() {
        //여기서 클래스 객체(Class<T>)가 필요 따라서 Class<T> entityClass 를 저장해서 기억
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    @Override
    public T findById(ID id) {
        return em.find(entityClass, id);
    }

    @Override
    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }
}
