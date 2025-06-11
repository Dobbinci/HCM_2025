package com.be.model;

import com.be.view.staff.MemberVisitor;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@DiscriminatorValue("Student")
@SuperBuilder
@NoArgsConstructor
public class Student extends Member {
    @Override
    public void accept(MemberVisitor visitor) {
        visitor.visit(this);
    }
}
