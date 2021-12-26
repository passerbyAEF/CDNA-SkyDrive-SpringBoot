package com.example.cdnaskydrivejava.controller;

import com.alibaba.fastjson.JSON;
import com.example.cdnaskydrivejava.model.UserMode;
import com.example.cdnaskydrivejava.util.BaseController;
import com.example.cdnaskydrivejava.util.RedisUtil;
import com.example.cdnaskydrivejava.util.ReturnMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 登录Controller
 */
@ResponseBody
@Controller
@RequestMapping("api")
public class LoginController extends BaseController {

    @Autowired
    com.example.cdnaskydrivejava.service.impl.UserServiceImpl UserServiceImpl;
    @Autowired
    RedisUtil redisUtil;

    @PostMapping("login")
    ReturnMode<Object> Login(HttpServletResponse response, @RequestParam String Name, @RequestParam String Pwds) throws IOException {
        UserMode user = new UserMode();
        user.setName(Name);
        user.setPwd(Pwds);
        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPwd())) {
            response.sendRedirect("/login?meg=null");
            return Error("用户名或密码为空！");
        }
        UserMode userMode = (UserMode) UserServiceImpl.loadUserByUsername(user.getUsername());
        if (userMode == null) {
            response.sendRedirect("/login?meg=usernull");
            return Error("用户不存在！");
        }
        if (!userMode.getPassword().equals(user.getPassword())) {
            response.sendRedirect("/login?meg=uperror");
            return Error("用户名或密码错误！");
        }
        String hashStr = DigestUtils.md5DigestAsHex((userMode.getName() + ":" + new Date()).getBytes());
        redisUtil.addAndSetTimeOut(hashStr, JSON.toJSONString(userMode));
        Cookie cookie = new Cookie("Token", hashStr);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("/");
        return OK(hashStr);
    }
}
