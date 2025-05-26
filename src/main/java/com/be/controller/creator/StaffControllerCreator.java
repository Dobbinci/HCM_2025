package com.be.controller.creator;

import com.be.controller.BaseController;
import com.be.controller.StaffController;
import jakarta.persistence.EntityManager;

public class StaffControllerCreator implements ControllerCreator {

    @Override
    public BaseController create(EntityManager em) {
        return new StaffController(em);
    }
}
