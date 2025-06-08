package com.be.controller;

import com.be.model.Member;
import com.be.model.Professor;
import com.be.model.Staff;
import com.be.model.Student;
import com.be.repository.MemberRepository;
import com.be.repository.impl.MemberRepositoryImpl;
import com.be.view.Authentication.Observer.SignupCelebrationObserver;
import com.be.view.Authentication.Observer.SignupSubject;
import jakarta.persistence.EntityManager;

public class MemberControllerFacade implements BaseController {
    private final EntityManager em;

    public MemberControllerFacade(EntityManager em) {
        this.em = em;
    }

    // 멤버 저장 로직
    public void saveMember(String memberId, String name, String systemId, String password, String position, String hanmadi, boolean isSocialSignup) {

        MemberRepository memberRepo = new MemberRepositoryImpl(em);

        if (memberRepo.findByMemberId(memberId) != null) {
            System.out.println("이미 가입이 되어있습니다.");
            return;
        }

        if (memberRepo.findBySystemId(systemId) != null) {
            System.out.println("이미 존재하는 ID입니다.");
            return;
        }

        Member member = null;
        if (position.equalsIgnoreCase("student")) {
            Student.StudentBuilder builder;
            builder = Student.builder()
                    .studentId(memberId)
                    .name(name)
                    .position(position)
                    .hanmadi(hanmadi);

            if (isSocialSignup) {
                builder.socialId(systemId).socialPassword(password);
            } else {
                builder.systemId(systemId).password(password);
            }

            member = builder.build();
        } else if (position.equalsIgnoreCase("professor")) {
            Professor.ProfessorBuilder builder = Professor.builder()
                    .professorId(memberId)
                    .name(name)
                    .position(position)
                    .hanmadi(hanmadi);

            if (isSocialSignup) {
                builder.socialId(systemId).socialPassword(password);
            } else {
                builder.systemId(systemId).password(password);
            }

            member = builder.build();
        } else if (position.equalsIgnoreCase("staff")) {
            Staff.StaffBuilder builder = Staff.builder()
                    .staffId(memberId)
                    .name(name)
                    .position(position)
                    .hanmadi(hanmadi);

            if (isSocialSignup) {
                builder.socialId(systemId).socialPassword(password);
            } else {
                builder.systemId(systemId).password(password);
            }

            member = builder.build();
        } else {
            System.out.println("잘못된 포지션 입력입니다.");
            return;
        }

// 저장 로직
        memberRepo.saveMember(member);
        System.out.println(name + "님의 가입을 환영합니다.");

        SignupSubject subject = new SignupSubject();
        subject.addObserver(new SignupCelebrationObserver());
        subject.notifyObservers(member);
    }

    public Member login(String systemId, String password, boolean isSocialLogin) {
        MemberRepository memberRepo = new MemberRepositoryImpl(em);
        Member member = null;

        if (isSocialLogin) {
            member = memberRepo.findBySocialId(systemId);
            if (member == null) {
                System.out.println("존재하지 않는 ID입니다.");
                return null;
            } else if (!member.getSocialPassword().equals(password)) {
                System.out.println("비밀번호가 일치하지 않습니다.");
                return null;
            }

        } else {
            member = memberRepo.findBySystemId(systemId);
            if (member == null) {
                System.out.println("존재하지 않는 ID입니다.");
                return null;
            } else if (!member.getPassword().equals(password)) {
                System.out.println("비밀번호가 일치하지 않습니다.");
                return null;
            }
        }
        return member;
    }
}