package com.naver.logintest.member.exception;

public class NotFoundUserException extends RuntimeException{

    public NotFoundUserException(String message){
        System.out.println(message);
    }
}
