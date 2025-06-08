package com.be.view.Authentication.Observer;

import com.be.model.Member;

public interface SignupObserver {
    void onSignupSuccess(Member member);
}
