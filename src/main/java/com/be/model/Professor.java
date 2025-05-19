package com.be.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Professor")
public class Professor extends Member {


    public Professor() {
        // JPA requires a default constructor
    }
}
