package com.be.controller.creator;

import com.be.controller.BaseController;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

public interface ControllerCreator {
    BaseController create(EntityManager em);
}
