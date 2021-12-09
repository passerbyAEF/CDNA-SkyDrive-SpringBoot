package com.example.cdnaskydrivejava.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.cdnaskydrivejava.model.UserMode;
import com.example.cdnaskydrivejava.service.UserService;
import com.example.cdnaskydrivejava.util.RedisUtil;
import com.example.cdnaskydrivejava.util.ReturnMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@ResponseBody
@Controller
@RequestMapping("api")
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;

    @PostMapping("Login")
    ReturnMode<String> Login(HttpServletResponse response, @RequestBody UserMode user) {
        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPwd())) {
            return new ReturnMode<>("用户名或密码为空！", "Error");
        }
        UserMode userMode = userService.getOne(new QueryWrapper<UserMode>().eq("UserName", user.getName()).eq("PassWord", user.getPwd()));
        if (userMode == null) {
            return new ReturnMode<>("用户名密码错误", "Error");
        }
        String hashStr = DigestUtils.md5DigestAsHex((userMode.getId() + ":" + new Date().toString()).getBytes());
        redisUtil.add(hashStr, JSON.toJSONString(userMode));
        response.addCookie(new Cookie("Token", hashStr));
        return new ReturnMode<>(hashStr, "OK");
    }
}
