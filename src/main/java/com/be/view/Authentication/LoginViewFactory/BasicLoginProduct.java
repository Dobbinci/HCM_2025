package com.be.view.Authentication.LoginViewFactory;

import com.be.controller.MemberControllerFacade;
import com.be.view.Authentication.LoginViewStrategy.BasicLoginView;

//Concrete Product
public class BasicLoginProduct implements LoginViewProduct{

    @Override
    public BasicLoginView returnInstance(MemberControllerFacade memberControllerFacade) {
        return new BasicLoginView(memberControllerFacade);
    }
}
