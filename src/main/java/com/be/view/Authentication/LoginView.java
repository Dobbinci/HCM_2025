package com.be.view.Authentication;

import com.be.controller.MemberController;
import com.be.model.Professor;
import com.be.model.Staff;
import com.be.model.Member;
import com.be.model.Student;
import com.be.view.Authentication.LoginViewStrategy.BasicLoginView;
import com.be.view.Authentication.SignUpViewStrategy.BasicSignUpView;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class LoginView extends TemplateLoginView {
    public LoginView(EntityManager em) {
        super(new BasicLoginView(em, new Scanner(System.in)),
                new BasicSignUpView(em, new Scanner(System.in)));
    }
}

