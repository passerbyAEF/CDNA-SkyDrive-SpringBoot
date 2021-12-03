package com.example.cdnaskydrivejava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller()
public class LoginController {

    @PostMapping("api/Login")
    String Login(@RequestParam String Name, String Pwds) {
        return "123";
    }
}
