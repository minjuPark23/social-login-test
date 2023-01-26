package com.naver.logintest.oauth;

import com.naver.logintest.member.entity.Member;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@Getter
@ToString
public class PrincipalDetails extends User implements OAuth2User, OidcUser {

    private Member member;
    private Map<String, Object> attributes;

    public PrincipalDetails(Member member) {
        super(member.getEmail(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRoleType()));
        this.member = member;
    }

    public PrincipalDetails(Member member, Map<String, Object> attributes) {
        this(member);
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getClaims() {
        return null;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return member.getEmail();
    }
}
