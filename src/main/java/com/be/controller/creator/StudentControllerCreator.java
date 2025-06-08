package com.be.controller.creator;

import com.be.controller.BaseController;
import com.be.controller.StudentControllerFacade;
import jakarta.persistence.EntityManager;

public class StudentControllerCreator implements ControllerCreator {

    @Override
    public BaseController create(EntityManager em) {
        return new StudentControllerFacade(em);
    }
}
