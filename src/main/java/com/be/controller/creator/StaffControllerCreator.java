package com.be.controller.creator;

import com.be.controller.BaseController;
import com.be.controller.StaffControllerFacade;
import jakarta.persistence.EntityManager;

public class StaffControllerCreator implements ControllerCreator {

    @Override
    public BaseController create(EntityManager em) {
        return new StaffControllerFacade(em);
    }
}
