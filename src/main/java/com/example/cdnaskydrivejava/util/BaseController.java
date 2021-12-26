package com.example.cdnaskydrivejava.util;

import com.example.cdnaskydrivejava.model.UserMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    protected ReturnMode<Object> Error(String message){
        return new ReturnMode<>(message, "Error");
    }
    protected ReturnMode<Object> OK(Object data){
        return new ReturnMode<>(data, "OK");
    }
    protected UserMode getUser(){
        return (UserMode)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
