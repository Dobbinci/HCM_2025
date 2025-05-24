package com.be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Entity
@Getter
@Setter
//@Builder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "member_type")
public class Member {

    @Id
    private Long id; //학번,교번

    @Column(nullable = false, unique = true)
    private String systemId;

    @Column(nullable = false)
    private String password;
    private String name;

    private String position;


    public Member(Long id, String systemId, String password, String name, String position) {
        this.id = id;
        this.systemId = systemId;
        this.password = password;
        this.name = name;
        this.position = position;
    }
}
