package com.example.cdnaskydrivejava.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.cdnaskydrivejava.model.UserMode;
import com.example.cdnaskydrivejava.service.impl.UserServiceImpl;
import com.example.cdnaskydrivejava.util.BaseController;
import com.example.cdnaskydrivejava.util.RedisUtil;
import com.example.cdnaskydrivejava.util.ReturnMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 关于用户信息和相关操作的Controller
 */
@Controller
@ResponseBody
@RequestMapping("api")
public class UserController extends BaseController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    RedisUtil redisUtil;

    //现在此方法只获取用户名，因为前端只用到这个数据
    @GetMapping("GetUserData")
    public ReturnMode<Object> getUserData(){
        UserMode user = (UserMode) getUser().getPrincipal();
        JSONObject json=new JSONObject();
        json.put("name",user.getName());
        return OK(json);
    }

    //登录
    @PostMapping("Register")
    public ReturnMode<Object> register(HttpServletResponse response, @RequestParam String Name, @RequestParam String Pwds, @RequestParam String PhoneNumber) throws IOException {
        if (!userService.Register(Name, Pwds, PhoneNumber)) {
            response.sendRedirect("/Register?meg=usererror");
            return Error("用户已存在");
        }
        UserMode userMode = (UserMode) userService.loadUserByUsername(Name);
        String hashStr = DigestUtils.md5DigestAsHex((Name + ":" + new Date()).getBytes());
        redisUtil.addAndSetTimeOut(hashStr, JSON.toJSONString(userMode));
        Cookie cookie = new Cookie("Token", hashStr);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("/");
        return OK(hashStr);
    }
}
