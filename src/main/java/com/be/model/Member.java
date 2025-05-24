package com.be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "member_type")
@SuperBuilder
public class Member {

    @Id
    private Long id; //학번,교번

    @Column(nullable = false, unique = true)
    private String systemId;

    @Column(nullable = false)
    private String password;
    private String name;

    private String position;
}
