package com.be.repository;

import com.be.model.Member;

public interface MemberRepository {

    void save(Member member);
    Member findById(Long id);
}
