package com.be.controller.factory;

import com.be.controller.BaseController;
import com.be.controller.creator.ControllerCreator;
import com.be.controller.creator.ProfessorControllerCreator;
import com.be.controller.creator.StaffControllerCreator;
import com.be.controller.creator.StudentControllerCreator;
import com.be.model.Member;
import com.be.model.Professor;
import com.be.model.Staff;
import com.be.model.Student;
import jakarta.persistence.EntityManager;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {
    private static final Map<Class<?>, ControllerCreator> creators = new HashMap<>();

    static {
        creators.put(Professor.class, new ProfessorControllerCreator());
        creators.put(Staff.class, new StaffControllerCreator());
        creators.put(Student.class, new StudentControllerCreator());
    }
    // Factory method
    public static BaseController getController(Member member , EntityManager em) {
        ControllerCreator creator = creators.get(member.getClass());
        if (creator != null) {
            return creator.create(em);
        } else {
            throw new IllegalArgumentException("No controller found for member type: " + member.getClass());
        }
    }
}
