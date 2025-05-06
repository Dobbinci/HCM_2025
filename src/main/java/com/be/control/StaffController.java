package com.be.control;

import java.util.List;
import com.be.control.MemberManager;
import com.be.service.Member;
import com.be.service.Student;

public class StaffController {
    private final MemberManager memberManager = MemberManager.getInstance();

    public List<Member> getAllMembers() {
        return memberManager.getAllMembers();
    }

    public List<Member> getProfessors() {
        return memberManager.getProfessors();
    }

    public List<Member> getStudnets() {
        return memberManager.getStudents();
    }

    public List<Member> getStaffs() {
        return memberManager.getStaffs();
    }

}
