package com.naver.logintest.oauth.dto;

import com.naver.logintest.member.entity.Member;
import lombok.Getter;

@Getter
public class SessionUser {
    private final String name;
    private final String email;

    public SessionUser(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
