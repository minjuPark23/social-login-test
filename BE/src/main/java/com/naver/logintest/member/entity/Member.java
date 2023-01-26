package com.naver.logintest.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String email;

    @Column
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth_day")
    private String birthDay;

    @Enumerated(EnumType.STRING)
    private ProviderType provider;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column
    private String password;

    @Builder
    public Member(String email, String name, String phoneNumber, String birthDay, ProviderType provider, RoleType role, String password){
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.provider = provider;
        this.role = role;
        this.password = password;
    }

    public Member update(String name){
        this.name = name;
        return this;
    }

    public String getRoleType(){
        return this.getRole().toString();
    }
}
