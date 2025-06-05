package com.be.model;

import com.be.view.staff.MemberVisitor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("Professor")
public class Professor extends Member {
    @Override
    public void accept(MemberVisitor visitor) {
        visitor.visit(this);
    }
}
