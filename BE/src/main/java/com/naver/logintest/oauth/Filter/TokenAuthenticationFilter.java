package com.naver.logintest.oauth.Filter;

import com.naver.logintest.oauth.token.AuthToken;
import com.naver.logintest.oauth.token.AuthTokenProvider;
import com.naver.logintest.utils.HeaderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 토큰 꺼내기
        String tokenStr = HeaderUtil.getAccessToken(request);
        // token 반환
        AuthToken token = tokenProvider.convertAuthToken(tokenStr);

        // 인증된 토큰인지 확인
        if (token.validate()) {
            log.debug("is validate");
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
