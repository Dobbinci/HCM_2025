package com.be.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Student")
public class Student extends Member {


    public Student(Long id, String name, String systemId, String password, String position) {
        super(id, name, systemId, password, position);
    }

    public Student() {

    }
}
