package com.be.view.Authentication;

import java.util.Scanner;
import com.be.model.Member;
import com.be.view.Authentication.LoginViewStrategy.LoginViewStrategy;
import com.be.view.Authentication.SignUpViewStrategy.SignUpViewStrategy;

public abstract class TemplateLoginView {

    protected Scanner scanner = new Scanner(System.in);

    protected LoginViewStrategy loginStrategy;
    protected SignUpViewStrategy signupStrategy;

    public TemplateLoginView(LoginViewStrategy loginStrategy, SignUpViewStrategy signupStrategy) {
        this.loginStrategy = loginStrategy;
        this.signupStrategy = signupStrategy;
    }

    public final Member loginOrSignupFlow() {
        String checkWork = "";

        while (!(checkWork.equals("1") || checkWork.equals("2") || checkWork.equals("3"))) {
            System.out.print("\nWelcome to Handong Course Management System!\n" +
                    "What would you do?\n1. Login\n2. Sign up\n3. Exit\n");
            checkWork = scanner.nextLine();

            switch (checkWork) {
                case "1": //login
                    return loginStrategy.login();
                case "2": //signup
                    signupStrategy.signup();
                    checkWork = "restart"; // 루프 계속
                    break;
                case "3": //exit
                    System.out.println("Bye!");
                    System.exit(0);
                default:
                    System.out.println("Wrong work");
            }
        }
        return null;
    }
}
