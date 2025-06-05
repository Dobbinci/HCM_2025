package com.be.repository.impl;

import com.be.model.Member;
import com.be.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class MemberRepositoryImpl implements MemberRepository {

    private EntityManager em;

    public MemberRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void saveMember(Member member) {
        em.getTransaction().begin();
        em.persist(member);
        em.getTransaction().commit();
    }

    @Override
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public Member findBySystemId(String systemId) {
        try {
            return em.createQuery(
                            "SELECT m FROM Member m WHERE m.systemId = :systemId", Member.class)
                    .setParameter("systemId", systemId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // systemId 중복 없음
        }
    }

    @Override
    public Member findBySocialId(String socialId) {
        try {
            return em.createQuery(
                            "SELECT m FROM Member m WHERE m.socialId = :socialId", Member.class)
                    .setParameter("socialId", socialId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // systemId 중복 없음
        }
    }

    @Override
    public Member findByMemberId(String memberId) {
        try {
            return em.createQuery("""
                SELECT m FROM Member m 
                WHERE m.professorId = :memberId 
                   OR m.studentId = :memberId 
                   OR m.staffId = :memberId
            """, Member.class)
                    .setParameter("memberId", memberId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // memberId 중복 없음
        }
    }
}
