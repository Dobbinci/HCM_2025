package com.be.controller.creator;

import com.be.controller.BaseController;
import com.be.controller.StudentController;
import jakarta.persistence.EntityManager;

public class StudentControllerCreator implements ControllerCreator {

    @Override
    public BaseController create(EntityManager em) {
        return new StudentController(em);
    }
}
