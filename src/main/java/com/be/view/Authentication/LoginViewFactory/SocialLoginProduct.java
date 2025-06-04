package com.be.view.Authentication.LoginViewFactory;

import com.be.controller.MemberController;
import com.be.view.Authentication.LoginViewStrategy.LoginViewStrategy;
import com.be.view.Authentication.LoginViewStrategy.SocialLoginView;

//Concrete Product
public class SocialLoginProduct implements LoginViewProduct {
    @Override
    public LoginViewStrategy returnInstance(MemberController memberController) {
        return new SocialLoginView(memberController);
    }
}
