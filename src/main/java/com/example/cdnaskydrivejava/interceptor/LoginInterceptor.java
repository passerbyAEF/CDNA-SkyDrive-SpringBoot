package com.example.cdnaskydrivejava.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie token;
        Cookie username;
        for(Cookie c:request.getCookies()){
            if(c.getName().equals("Token")){
                token=c;
            }
            if(c.getName().equals("UserName")){
                username=c;
            }
        }
        return true;
    }
}
