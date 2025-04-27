package com.be.view;

import java.util.Scanner;
import com.be.control.MemberManager;

public class SignupView {

    private MemberManager memberManager;

    Scanner scanner = new Scanner(System.in);
    String id="";
    String password="";
    String name="";
    String userid="";
    String position="";

    public SignupView() {
        this.memberManager = new MemberManager();
    }
    public void signup() {

        System.out.println("학번/직번을 입력하세요");
        userid = scanner.nextLine();
        System.out.println("신분을 입력하세요");
        position = scanner.nextLine();
        position=position.toLowerCase();
        if(position.equals("student")||position.equals("professor")||position.equals("staff")) {
            if (memberManager.checkUserRegistration(userid, position)) {
                System.out.println("이미 가입이 되어있습니다");
            } else {
                boolean sameidcheck = true;
                while (sameidcheck) {
                    System.out.println("이름을 입력하세요");
                    name = scanner.nextLine();
                    System.out.println("ID를 입력하세요: ");
                    id = scanner.nextLine();
                    System.out.println("비밀번호를 입력하세요: ");
                    password = scanner.nextLine();
                    sameidcheck = memberManager.sameidcheck(id);
                }
                memberManager.saveUser(id, password, name, userid, position);
                System.out.println(name + "님의 가입을 환영합니다.");
            }
        }
        else{
            System.out.println("잘못된 신분입니다.");
        }
    }
}
