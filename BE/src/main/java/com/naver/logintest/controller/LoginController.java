package com.naver.logintest.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naver.logintest.TokenVo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

@Slf4j
@RestController
//@RequestMapping("/naver")
public class LoginController {

    private static String CLIENT_ID = "MhMhgUVobLYoR3WjY_1S";
    private static String CLIENT_SECRET = "WpRz7ZkEFE";

    @GetMapping("/oauth")
    public String naverConnect() throws UnsupportedEncodingException {
        log.info("네이버 로그인 바로가기");

        String REDIRECT_URL = URLEncoder.encode("http://localhost:8080/login/oauth2/code/naver", "UTF-8");
        SecureRandom secureRandom = new SecureRandom();
        String state = new BigInteger(130, secureRandom).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += "&client_id=" + CLIENT_ID;
        apiURL += "&redirect_uri=" + REDIRECT_URL;
        apiURL += "&state=" + state;

        // 네이버 로그인 화면으로 리다이렉트
        return "redirect:"+apiURL;
    }

    @RequestMapping(value = "/callback", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public void getAccessToken(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state, HttpServletResponse response) throws UnsupportedEncodingException {
        log.info("토큰 발급/갱신/삭제 요청 URL");

        String REDIRECT_URL = URLEncoder.encode("http://localhost:8080/login/oauth2/code/naver", "UTF-8");
        String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLIENT_SECRET;
        apiURL += "&redirect_uri=" + REDIRECT_URL;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;

        try {
            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            log.info("responseCode = "+responseCode);
            BufferedReader br;
            if (responseCode == 200) {
                // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }else {
                // 에러 발생
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null){
                res.append(inputLine);
            }
            br.close(); // autoCloser

            // String to token
            TokenVo token = new ObjectMapper().readValue(res.toString(), TokenVo.class);
            log.info("1.token.getAccess_token(): " + token.getAccess_token());
            log.info("2.toke.getToken_type() "+token.getToken_type());

            response.setHeader("Authorization", "Bearer " + token.getAccess_token());
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @GetMapping("/getProfile")
    public void apiExamMemberProfile(){
        // 헤더에서 토큰 줘야함
    }
}
