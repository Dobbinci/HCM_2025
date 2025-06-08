package com.be.view.Authentication.Observer;
import com.be.model.Member;

public class SignupCelebrationObserver implements SignupObserver {
    @Override
    public void onSignupSuccess(Member member) {
        System.out.println("\n🎉 회원가입을 축하합니다, " + member.getName() + "님! 🎉");
        System.out.println("************************************");
        System.out.println("*     ⭐ Welcome to HCM! ⭐        *");
        System.out.println("************************************");
    }
}
