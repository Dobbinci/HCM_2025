package com.be.view.Authentication.LoginViewFactory;

import com.be.controller.MemberControllerFacade;
import com.be.view.Authentication.LoginViewStrategy.LoginViewStrategy;
import com.be.view.Authentication.LoginViewStrategy.SocialLoginView;

//Concrete Product
public class SocialLoginProduct implements LoginViewProduct {
    @Override
    public LoginViewStrategy returnInstance(MemberControllerFacade memberControllerFacade) {
        return new SocialLoginView(memberControllerFacade);
    }
}
