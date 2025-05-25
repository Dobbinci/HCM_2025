package com.be.view.Authentication;

import com.be.model.Member;
import com.be.view.Authentication.LoginViewStrategy.LoginViewStrategy;

public class LoginContext {
    private LoginViewStrategy strategy;

    public LoginContext(LoginViewStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(LoginViewStrategy strategy) {
        this.strategy = strategy;
    }

    public Member executeLogin() {
        return strategy.login();
    }
}
