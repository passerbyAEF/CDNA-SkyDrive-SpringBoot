package com.example.cdnaskydrivejava.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.cdnaskydrivejava.model.UserMode;

public interface UserService extends IService<UserMode> {

    //获取用户的根目录ID
    Integer getRootUrlId(UserMode user);

    Boolean Register(String Name, String Pwds, String PhoneNumber);
}
