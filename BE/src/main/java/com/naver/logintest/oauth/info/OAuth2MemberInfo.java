package com.naver.logintest.oauth.info;

import java.util.Map;

/**
 * 필요한 데이터
 */
public interface OAuth2MemberInfo {
    Map<String, Object> getAttributes();

    String getProviderId();

    String getProvider();

    String getEmail();

    String getName();

    // TODO: phoneNumber, birthDay
    String getPhoneNumber();

    String getBirthDay();
}
