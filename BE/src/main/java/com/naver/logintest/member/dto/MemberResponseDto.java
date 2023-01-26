package com.naver.logintest.member.dto;

import com.naver.logintest.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;
    private String birth;
    private String phone;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.birth = member.getBirthDay();
        this.phone = member.getPhoneNumber();
    }

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member);
    }
}
