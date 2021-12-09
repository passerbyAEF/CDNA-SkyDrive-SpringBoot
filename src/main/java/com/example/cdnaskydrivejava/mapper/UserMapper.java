package com.example.cdnaskydrivejava.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cdnaskydrivejava.model.UserMode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserMode> {
}
