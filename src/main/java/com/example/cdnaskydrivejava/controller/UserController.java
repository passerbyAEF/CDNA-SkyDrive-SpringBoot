package com.example.cdnaskydrivejava.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.cdnaskydrivejava.model.UserMode;
import com.example.cdnaskydrivejava.util.BaseController;
import com.example.cdnaskydrivejava.util.ReturnMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("api")
public class UserController extends BaseController {

    @GetMapping("GetUserData")
    public ReturnMode<Object> getUserData(){
        UserMode user = (UserMode) getUser().getPrincipal();
        JSONObject json=new JSONObject();
        json.put("name",user.getName());
        return OK(json);
    }

    @PostMapping("Register")
    public ReturnMode<Object> register(){
        return OK("");
    }
}
