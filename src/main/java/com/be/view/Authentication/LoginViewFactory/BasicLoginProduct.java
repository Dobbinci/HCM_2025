package com.be.view.Authentication.LoginViewFactory;

import com.be.controller.MemberController;
import com.be.model.Member;
import com.be.view.Authentication.LoginViewStrategy.BasicLoginView;

//Concrete Product
public class BasicLoginProduct implements LoginViewProduct{

    @Override
    public BasicLoginView returnInstance(MemberController memberController) {
        return new BasicLoginView(memberController);
    }
}
