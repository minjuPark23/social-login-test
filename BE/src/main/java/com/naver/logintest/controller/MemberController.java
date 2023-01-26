package com.naver.logintest.controller;

import com.naver.logintest.member.dto.MemberResponseDto;
import com.naver.logintest.member.entity.Member;
import com.naver.logintest.member.service.MemberSerivce;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class MemberController {

    private final MemberSerivce memberSerivce;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> fetchUser(Member member) {
        log.debug("/me");
        return ResponseEntity.ok(new MemberResponseDto(member));
    }
}
