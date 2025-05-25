package com.be.controller.creator;

import com.be.controller.BaseController;
import com.be.controller.ProfessorController;
import jakarta.persistence.EntityManager;

public class ProfessorControllerCreator implements ControllerCreator {

    @Override
    public BaseController create(EntityManager em) {
        return new ProfessorController(em);
    }
}
