package com.naver.logintest.config;

import com.naver.logintest.config.properties.AppProperties;
import com.naver.logintest.config.properties.CorsProperties;
import com.naver.logintest.oauth.Filter.TokenAuthenticationFilter;
import com.naver.logintest.oauth.Service.PrincipalDetailsService;
import com.naver.logintest.oauth.Service.PrincipalOauth2MemberService;
import com.naver.logintest.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.naver.logintest.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.naver.logintest.oauth.repository.MemberRefreshTokenRepository;
import com.naver.logintest.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.naver.logintest.oauth.token.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

//    private final CustomOAuth2UserService customOAuth2UserService;
    private final PrincipalOauth2MemberService principalOauth2MemberService;
    private final AuthTokenProvider tokenProvider;
    private final AppProperties appProperties;
    private final MemberRefreshTokenRepository memberRefreshTokenRepository;
    private final PrincipalDetailsService principalDetailsService;
    private final CorsProperties corsProperties;



    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                    .cors()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .csrf().disable()
                    .headers().frameOptions().disable()
                .and()
                // authorizeRequest ?????? ?????? ?????? ??????
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    // oauth ????????? ??????
                    .oauth2Login()
                    // ????????? ?????? ?????? ?????????
                    // "/oauth2/authorization"??? ????????? oauth ????????? ??????
                    .authorizationEndpoint()
                    .baseUri("/oauth2/authorization")
                    .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
                .and()
                    // callback ??????
                    .redirectionEndpoint()
                    .baseUri("/*/oauth2/code/*")
                .and()
                    // user ??????
                    .userInfoEndpoint()
                    // oauth??? ?????? ????????? ???????????? ?????? oauth2 ?????? ?????? ????????? ??????????????? ????????? ????????? ?????????
                    .userService(principalOauth2MemberService)
                // ?????? ??????/??????
                .and()
                    // ??????????????? ?????? ???????????? ???????????? ???????????? ?????????(JWT)
                    .successHandler(oAuth2AuthenticationSuccessHandler())
                    .failureHandler(oAuth2AuthenticationFailureHandler());

        // ????????? ????????? ????????? usernamepasswordAuthenticationToken????????? ????????? ?????? ??????
        return http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).build();
    }

    /*
     * Spring Security?????? ????????? ???????????? AuthenticationManager auth ????????? ??????
     * */
//    @Bean
//    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    /*
    ?????? ?????? ??????
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    /**
     * security ?????? ???, ????????? ?????????
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Oauth ?????? ?????? ?????????
     * */
    @Bean
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler(
                tokenProvider,
                appProperties,
                memberRefreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository()
        );
//        return new OAuth2AuthenticationSuccessHandler();
    }

    /*
     * Oauth ?????? ?????? ?????????
     * */
    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
//        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
        return new OAuth2AuthenticationFailureHandler();
    }

    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository(){
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(principalDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    /*
     * Cors ??????
     * */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
        corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split(",")));
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(corsConfig.getMaxAge());

        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
        return corsConfigSource;
    }
}