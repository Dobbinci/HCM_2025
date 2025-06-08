package com.be.view.Authentication.Observer;

import com.be.model.Member;

import java.util.ArrayList;
import java.util.List;

public class SignupSubject implements SignupSubjectInterface{//관찰 대상자
    private final List<SignupObserver> observers = new ArrayList<>();

    public void addObserver(SignupObserver observer) {
        observers.add(observer);
    }
    public void detach(SignupObserver  observer) {
        observers.remove(observer);
    }
    public void notifyObservers(Member member) {
        for (SignupObserver observer : observers) {
            observer.onSignupSuccess(member);
        }
    }
}
