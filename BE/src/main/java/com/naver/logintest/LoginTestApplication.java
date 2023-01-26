package com.naver.logintest;

import com.naver.logintest.config.properties.AppProperties;
import com.naver.logintest.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
		CorsProperties.class,
		AppProperties.class
})
@SpringBootApplication
public class LoginTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginTestApplication.class, args);
	}

}
