package com.example.cdnaskydrivejava.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cdnaskydrivejava.mapper.UserMapper;
import com.example.cdnaskydrivejava.model.UserMode;
import com.example.cdnaskydrivejava.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserMode> implements UserService {
}
