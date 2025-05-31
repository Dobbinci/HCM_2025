package com.be.repository;

import com.be.model.Member;

public interface MemberRepository {
    public void saveMember(Member member);
    Member findById(Long id);
    Member findBySystemId(String systemId);
    Member findByMemberId(String memberId);
    Member findBySocialId(String socialId);
}
