package com.be.view.Authentication.LoginViewFactory;

//Concrete Creator
public class BasicLoginFactory extends LoginViewFactory {

    @Override
    public LoginViewProduct getCreator() {
        return new BasicLoginProduct();
    }
}
