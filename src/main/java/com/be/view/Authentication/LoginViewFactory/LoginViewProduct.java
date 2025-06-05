package com.be.view.Authentication.LoginViewFactory;

import com.be.controller.MemberControllerFacade;
import com.be.view.Authentication.LoginViewStrategy.LoginViewStrategy;

//Product
public interface LoginViewProduct {
    LoginViewStrategy returnInstance(MemberControllerFacade memberControllerFacade);
}
