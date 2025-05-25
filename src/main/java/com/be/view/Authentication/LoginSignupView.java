package com.be.view.Authentication;

import com.be.controller.MemberController;
import com.be.view.Authentication.LoginViewStrategy.BasicLoginView;
import com.be.view.Authentication.LoginViewStrategy.SocialLoginView;
import com.be.view.Authentication.SignUpViewStrategy.BasicSignUpView;
import com.be.view.Authentication.SignUpViewStrategy.SocialSignUpView;
import jakarta.persistence.EntityManager;
import com.be.model.Member;

public class LoginSignupView extends TemplateLoginView {
    private MemberController memberController;
    public LoginSignupView(EntityManager em) {
        this.memberController = new MemberController(em);
    }

    protected Member login(){
        String checkWork = "";
        LoginContext context = new LoginContext(new BasicLoginView(memberController));
        while (!(checkWork.equals("1") || checkWork.equals("2"))) {
            System.out.print("\nWhat would you do?\n1. Basic Login\n2. Social Login\n");
            checkWork = scanner.nextLine();

            switch (checkWork) {
                case "1"://Basic login
                    return context.executeLogin();
                case "2":
                    context.setStrategy(new SocialLoginView(memberController));
                    return context.executeLogin();
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
        return null;
    }

    protected void signup(){
        String checkWork = "";
        SignUpContext context = new SignUpContext(new BasicSignUpView(memberController));
        while (!(checkWork.equals("1") || checkWork.equals("2"))) {
            System.out.print("\nWhat would you do?\n1. Basic Signup\n2. Social Signup\n");
            checkWork = scanner.nextLine();

            switch (checkWork) {
                case "1"://Basic signup
                    context.executeSignup();
                    break;
                case "2"://social
                    context.setStrategy(new SocialSignUpView(memberController));
                    context.executeSignup();
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}

