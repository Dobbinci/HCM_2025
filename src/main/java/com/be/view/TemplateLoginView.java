package com.be.view;

import java.util.Scanner;
import com.be.service.Member;

public abstract class TemplateLoginView {

    protected Scanner scanner = new Scanner(System.in);

    public final Member loginOrSignupFlow() {
        String checkWork = "";

        while (!(checkWork.equals("login") || checkWork.equals("signup") || checkWork.equals("exit"))) {
            System.out.print("login or sign up?(login, signup, exit): ");
            checkWork = scanner.nextLine().toLowerCase();

            switch (checkWork) {
                case "login":
                    return login();
                case "signup":
                    signup();
                    checkWork = "restart"; // 루프 계속
                    break;
                case "exit":
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
