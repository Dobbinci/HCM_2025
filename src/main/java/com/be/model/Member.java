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
    private String name;//이름
    private String position;//신분(학생,교수,스탭)

    @Column(unique = true)
    private String systemId;//id
    private String socialId;//social login id
    private String professorId;//교수번호
    private String staffId;//스탭번호
    private String studentId;//학번

    private String password;//비밀번호
    private String socialPassword;//social login 비밀번호

}
