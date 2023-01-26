package com.naver.logintest.member.service;

import com.naver.logintest.member.exception.NotFoundUserException;
import com.naver.logintest.member.repository.MemberRepository;
import com.naver.logintest.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSerivce {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto getUserInfo(String email){
        return memberRepository.findByEmail(email).map(MemberResponseDto::of).orElseThrow(() -> new NotFoundUserException("이메일을 가진 사람이 없습니다."));
    }
}
