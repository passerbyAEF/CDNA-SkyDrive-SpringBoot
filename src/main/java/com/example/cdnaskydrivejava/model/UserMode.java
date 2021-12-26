package com.example.cdnaskydrivejava.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 用于表示用户的实体类
 * 同时实现UserDetails接口对接Spring Security认证
 */
@Data
@TableName("usertable")
public class UserMode implements UserDetails {
    @TableId(value = "ID",type = IdType.AUTO)
    Integer id;
    @JsonProperty("Name")
    @TableField("UserName")
    String name;
    @JsonProperty("Pwds")
    @TableField("PassWord")
    String pwd;
    @TableField("DirID")
    Integer fileId;
    @TableField(select = false)
    List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
