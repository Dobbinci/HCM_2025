package com.be.view.Authentication.LoginViewFactory;

import com.be.controller.MemberController;
import com.be.view.Authentication.LoginViewStrategy.LoginViewStrategy;

//Product
public interface LoginViewProduct {
    LoginViewStrategy returnInstance(MemberController memberController);
}
