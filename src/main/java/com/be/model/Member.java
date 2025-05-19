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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "member_type")
public class Member {

    @Id
    Long id; //학번,교번

    private String systemId;
    private String password;
    private String name;
    private String position;


    public Member(String systemId, String password, String name, String position) {
        this.systemId = systemId;
        this.password = password;
        this.name = name;
        this.position = position;
    }
}
