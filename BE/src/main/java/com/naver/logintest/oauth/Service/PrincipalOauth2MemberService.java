package com.naver.logintest.oauth.Service;

import com.naver.logintest.member.entity.Member;
import com.naver.logintest.member.entity.ProviderType;
import com.naver.logintest.member.entity.RoleType;
import com.naver.logintest.member.repository.MemberRepository;
import com.naver.logintest.oauth.PrincipalDetails;
import com.naver.logintest.oauth.info.NaverMemberInfo;
import com.naver.logintest.oauth.info.OAuth2MemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 소셜로그인 처음인지 확인하기
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PrincipalOauth2MemberService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);

        //provider 판별
        ProviderType providerType = ProviderType.valueOf(request.getClientRegistration().getRegistrationId().toUpperCase());
        log.debug("현재 provider: " + providerType);
        OAuth2MemberInfo oAuthMemberInfo = null;

        if (providerType.equals(ProviderType.NAVER)) {
            log.debug("NAVER");
            oAuthMemberInfo = new NaverMemberInfo(oAuth2User.getAttributes());
        } else {
            log.debug("not NAVER");
            oAuthMemberInfo = new NaverMemberInfo(oAuth2User.getAttributes());
        }

        log.debug("oAuthMemberInfo: " + oAuthMemberInfo);
        Optional<Member> member = memberRepository.findByEmail(oAuthMemberInfo.getEmail());

        // DB에 없는 Member라면 회원가입
        if (member.isEmpty()) {
            log.debug("소셜 회원가입");
            Member newMember = createUser(oAuthMemberInfo, providerType);
            return new PrincipalDetails(newMember, oAuth2User.getAttributes());
        } else {
            log.debug("소셜 로그인");
            return new PrincipalDetails(member.get(), oAuth2User.getAttributes());
        }

    }

    private Member createUser(OAuth2MemberInfo oAuthMemberInfo, ProviderType providerType) {
        Member member = new Member(oAuthMemberInfo.getEmail(),
                oAuthMemberInfo.getName(),
                oAuthMemberInfo.getPhoneNumber(),
                oAuthMemberInfo.getBirthDay(),
                providerType,
                RoleType.ROLE_USER,
                "NO_PASSWORD");
        return memberRepository.save(member);
    }

}
