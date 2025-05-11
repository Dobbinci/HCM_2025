package com.be.view;

import java.util.Scanner;
import com.be.model.Member;

public abstract class TemplateLoginView {

    protected Scanner scanner = new Scanner(System.in);

    public final Member loginOrSignupFlow() {
        String checkWork = "";

        while (!(checkWork.equals("1") || checkWork.equals("2") || checkWork.equals("3"))) {
            System.out.print("Welcome to Handong Course Management System!\n" +
                    "What would you do?\n1. Login\n2. Sign up\n3. Exit\n");
            checkWork = scanner.nextLine();

            switch (checkWork) {
                case "1": //login
                    return login();
                case "2": //signup
                    signup();
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

    protected abstract Member login();

    protected abstract void signup();
}
