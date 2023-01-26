package com.naver.logintest.oauth.repository;

import com.naver.logintest.member.entity.MemberRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRefreshTokenRepository extends JpaRepository<MemberRefreshToken, Long> {

    MemberRefreshToken findByEmail(String email);

    MemberRefreshToken findByEmailAndRefreshToken(String email, String refreshToken);
}
