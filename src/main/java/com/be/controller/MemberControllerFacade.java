package com.be.controller;

import com.be.model.Member;
import com.be.model.Professor;
import com.be.model.Staff;
import com.be.model.Student;
import com.be.repository.MemberRepository;
import com.be.repository.impl.MemberRepositoryImpl;
import jakarta.persistence.EntityManager;

public class MemberControllerFacade implements BaseController{
    private final EntityManager em;

    public MemberControllerFacade(EntityManager em) {
        this.em = em;
    }

    // 교수 멤버 저장 로직
    public void saveMember(Long id, String name, String systemId, String password, String position) {

        MemberRepository memberRepo = new MemberRepositoryImpl(em);

        if (memberRepo.findById(id) != null) {
            System.out.println("이미 가입이 되어있습니다.");
            return;
        }

        if (memberRepo.findBySystemId(systemId) != null) {
            System.out.println("이미 존재하는 ID입니다.");
            return;
        }

        Member member = null;
        if (position.equalsIgnoreCase("student")) {
            member = Student.builder()
                    .id(id)
                    .systemId(systemId)
                    .password(password)
                    .name(name)
                    .position(position)
                    .build();
        } else if (position.equalsIgnoreCase("professor")) {
            member = Professor.builder()
                    .id(id)
                    .systemId(systemId)
                    .password(password)
                    .name(name)
                    .position(position)
                    .build();
        } else if (position.equalsIgnoreCase("staff")) {
            member = Staff.builder()
                    .id(id)
                    .systemId(systemId)
                    .password(password)
                    .name(name)
                    .position(position)
                    .build();
        } else {
            System.out.println("잘못된 포지션 입력입니다.");
            return;
        }

// 저장 로직
        memberRepo.saveMember(member);
        System.out.println(name + "님의 가입을 환영합니다.");

    }

    public Member login(String systemId, String password) {
        MemberRepository memberRepo = new MemberRepositoryImpl(em);

        Member member = memberRepo.findBySystemId(systemId);
        if (member == null) {
            System.out.println("존재하지 않는 ID입니다.");
            return null;
        }

        if (!member.getPassword().equals(password)) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return null;
        }

        return member;
    }
}