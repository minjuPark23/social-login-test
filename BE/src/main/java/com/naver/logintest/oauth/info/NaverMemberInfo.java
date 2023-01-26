package com.naver.logintest.oauth.info;

import com.naver.logintest.member.entity.ProviderType;

import java.util.Map;

public class NaverMemberInfo implements OAuth2MemberInfo {

    private final Map<String, Object> attributes; //OAuth2User.getAttributes();
    private final Map<String, Object> attributesResponse;

    public NaverMemberInfo(Map<String, Object> attributes) {
        this.attributes = (Map<String, Object>) attributes.get("response");
        this.attributesResponse = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return attributesResponse.get("id").toString();
    }

    @Override
    public String getProvider() {
        return ProviderType.NAVER.toString();
    }

    @Override
    public String getEmail() {
        return attributesResponse.get("email").toString();
    }

    @Override
    public String getName() {
        return attributesResponse.get("name").toString();
    }

    @Override
    public String getPhoneNumber() {
        return attributesResponse.get("mobile").toString();
    }

    @Override
    public String getBirthDay() {
        return attributesResponse.get("birthyear").toString() + "-" + attributesResponse.get("birthday").toString();
    }
}
