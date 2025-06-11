package com.be.view.Authentication.LoginViewFactory;

import com.be.controller.MemberControllerFacade;
import com.be.view.Authentication.LoginViewStrategy.LoginViewStrategy;

//Creator
public abstract class LoginViewFactory {
    public LoginViewStrategy pullLoginView(MemberControllerFacade memberControllerFacade) {
        LoginViewProduct loginViewProduct = getCreator();
        return loginViewProduct.returnInstance(memberControllerFacade);
    }
    protected abstract LoginViewProduct getCreator();
}
