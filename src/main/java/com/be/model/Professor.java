package com.be.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Professor")
public class Professor extends Member {


    public Professor(Long id, String name, String systemId, String password, String position) {
        super(id, name, systemId, password, position);
    }

    public Professor() {

    }
}
