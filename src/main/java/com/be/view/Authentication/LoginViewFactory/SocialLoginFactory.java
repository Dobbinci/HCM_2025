package com.be.view.Authentication.LoginViewFactory;

//Concrete Creator
public class SocialLoginFactory extends LoginViewFactory {
    @Override
    public LoginViewProduct getCreator() {
        return new SocialLoginProduct();
    }
}
