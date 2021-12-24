package com.example.cdnaskydrivejava.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    protected ReturnMode<Object> Error(String message){
        return new ReturnMode<>(message, "Error");
    }
    protected ReturnMode<Object> OK(Object data){
        return new ReturnMode<>(data, "OK");
    }
    protected Authentication getUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
