package com.be.view.Authentication;

import com.be.controller.MemberControllerFacade;
import com.be.view.Authentication.LoginViewFactory.BasicLoginFactory;
import com.be.view.Authentication.LoginViewFactory.LoginViewFactory;
import com.be.view.Authentication.LoginViewFactory.SocialLoginFactory;
import com.be.view.Authentication.LoginViewStrategy.SocialLoginView;
import com.be.view.Authentication.SignUpViewStrategy.BasicSignUpView;
import com.be.view.Authentication.SignUpViewStrategy.SocialSignUpView;
import jakarta.persistence.EntityManager;
import com.be.model.Member;

public class LoginSignupView extends TemplateLoginView {
    private MemberControllerFacade memberControllerFacade;
    public LoginSignupView(EntityManager em) {
        this.memberControllerFacade = new MemberControllerFacade(em);
    }

    protected Member login(){
        String checkWork = "";
        //팩토리 선언
        LoginViewFactory factory = new BasicLoginFactory();
        LoginContext context = new LoginContext(factory.pullLoginView(memberControllerFacade)); //기존: new BasicView(memberController)

        while (!(checkWork.equals("1") || checkWork.equals("2"))) {
            System.out.print("\nWhat would you do?\n1. Basic Login\n2. Social Login\n");
            checkWork = scanner.nextLine();

            switch (checkWork) {
                case "1"://Basic login
                    return context.executeLogin();
                case "2":
                    factory = new SocialLoginFactory();
                    context.setStrategy(factory.pullLoginView(memberControllerFacade));

                    return context.executeLogin();
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
        return null;
    }

    protected void signup(){
        String checkWork = "";
        SignUpContext context = new SignUpContext(new BasicSignUpView(memberControllerFacade));
        while (!(checkWork.equals("1") || checkWork.equals("2"))) {
            System.out.print("\nWhat would you do?\n1. Basic Signup\n2. Social Signup\n");
            checkWork = scanner.nextLine();

            switch (checkWork) {
                case "1"://Basic signup
                    context.executeSignup();
                    break;
                case "2"://social
                    context.setStrategy(new SocialSignUpView(memberControllerFacade));
                    context.executeSignup();
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}

