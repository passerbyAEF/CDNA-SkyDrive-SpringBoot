package com.example.cdnaskydrivejava.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cdnaskydrivejava.mapper.UserMapper;
import com.example.cdnaskydrivejava.model.UserMode;
import com.example.cdnaskydrivejava.service.UserService;
import com.example.cdnaskydrivejava.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserMode> implements UserService, UserDetailsService {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserMode user = this.getOne(new QueryWrapper<UserMode>().eq("UserName", s));
        if(user==null) return null;
        user.setAuthorities(getAuthorities());
        return user;
    }

    /**
     * 获取用户的角色权限,当前设计所有用户都是统一权限所以只会设置一个默认的权限
     */
    private List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authList;
    }

}
