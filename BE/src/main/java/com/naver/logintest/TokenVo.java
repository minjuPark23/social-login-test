package com.naver.logintest;

import lombok.Getter;

@Getter
public class TokenVo {

    private String access_token;
    private String refresh_token;
    private String token_type;
    private String expires_in;
}
