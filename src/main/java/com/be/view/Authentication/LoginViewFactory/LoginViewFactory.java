package com.be.view.Authentication.LoginViewFactory;

import com.be.controller.MemberController;
import com.be.view.Authentication.LoginViewStrategy.LoginViewStrategy;

//Creator
public abstract class LoginViewFactory {
    protected abstract LoginViewProduct getCreator();

    public LoginViewStrategy selectLoginView(MemberController memberController) {
        LoginViewProduct loginViewProduct = getCreator();
        return loginViewProduct.returnInstance(memberController);
    }
}
