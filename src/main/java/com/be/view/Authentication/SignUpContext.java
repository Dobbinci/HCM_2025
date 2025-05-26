package com.be.view.Authentication;

import com.be.view.Authentication.SignUpViewStrategy.SignUpViewStrategy;

public class SignUpContext {
    private SignUpViewStrategy strategy;

    public SignUpContext(SignUpViewStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SignUpViewStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeSignup() {
        strategy.signup();
    }
}
