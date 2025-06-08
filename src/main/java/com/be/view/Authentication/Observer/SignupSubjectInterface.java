package com.be.view.Authentication.Observer;

import com.be.model.Member;

public interface SignupSubjectInterface {
    void addObserver(SignupObserver observer);
    void detach(SignupObserver observer);
    void notifyObservers(Member member);
}
