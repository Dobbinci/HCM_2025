package com.be.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Staff")
public class Staff extends Member {

    public Staff(Long id, String name, String systemId, String password, String position) {
        super(id, name, systemId, password, position);
    }

    public Staff() {

    }
}
