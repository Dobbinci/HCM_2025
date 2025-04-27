package com.be.view;

import java.util.Scanner;
import com.be.control.MemberManager;
import com.be.service.Professor;
import com.be.service.Staff;
import com.be.service.Student;
import com.be.service.Member;

public class LoginView {

    private MemberManager memberManager; // 필드로 선언

    // 생성자로 memberManager 받아오기
    public LoginView(MemberManager memberManager) {
        this.memberManager = memberManager;
    }

    public Member login() {
        Scanner scanner = new Scanner(System.in);
        Member loggedInMember = null;
        String checkWork="start";
        while(!(checkWork.equals("login") || checkWork.equals("signup"))) {
            System.out.print("login or sign up?(login, signup, exit): ");
            checkWork=scanner.nextLine();
            checkWork = checkWork.toLowerCase();
            if(checkWork.equals("login")) {
                while (loggedInMember == null) { // 로그인 성공할 때까지 반복
                    System.out.println("아이디와 비밀번호를 입력하시오!");
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("PW: ");
                    String pw = scanner.nextLine();

                    loggedInMember = memberManager.manageLogin(id, pw);

                    if (loggedInMember == null) {
                        System.out.println("로그인 실패! 아이디 또는 비밀번호가 올바르지 않습니다.\n다시 입력하세요.");
                    }
                }

                if (loggedInMember instanceof Professor) {
                    Professor professor = (Professor) loggedInMember;
                    System.out.println("로그인 성공! 환영합니다, " + professor.getName() + " 교수님!");
                } else if (loggedInMember instanceof Student) {
                    Student student = (Student) loggedInMember;
                    System.out.println("로그인 성공! 환영합니다, " + student.getName() + " 학생님!");
                } else if (loggedInMember instanceof Staff) {
                    Staff staff = (Staff) loggedInMember;
                    System.out.println("로그인 성공! 환영합니다, " + staff.getName() + " 직원님!");
                }

                return loggedInMember;
            }
            else if(checkWork.equals("signup")) {
                signup();
                checkWork="restart";
            }
            else if(checkWork.equals("exit")) {
                System.out.println("Bye!");
                System.exit(0);
            }
            else{
                System.out.println("Wrong work");
            }
        }
        return null;
    }

    public void signup() {

        Scanner scanner = new Scanner(System.in);
        String id="";
        String password="";
        String name="";
        String userid="";
        String position="";

        System.out.println("학번/직번을 입력하세요");
        userid = scanner.nextLine();
        System.out.println("신분을 입력하세요");
        position = scanner.nextLine();
        position=position.toLowerCase();
        if(position.equals("student")||position.equals("professor")||position.equals("staff")) {
            if (memberManager.checkMemberRegistration(userid, position)) {
                System.out.println("이미 가입이 되어있습니다");
            } else {
                boolean sameidCheck = true;
                while (sameidCheck) {
                    System.out.println("이름을 입력하세요");
                    name = scanner.nextLine();
                    System.out.println("ID를 입력하세요: ");
                    id = scanner.nextLine();
                    System.out.println("비밀번호를 입력하세요: ");
                    password = scanner.nextLine();
                    sameidCheck = memberManager.sameidCheck(id);
                }
                memberManager.saveMember(id, password, name, userid, position);
                System.out.println(name + "님의 가입을 환영합니다.");
            }
        }
        else{
            System.out.println("잘못된 신분입니다.");
        }
    }
}
