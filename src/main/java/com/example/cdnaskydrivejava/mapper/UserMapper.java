package com.example.cdnaskydrivejava.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cdnaskydrivejava.model.UserMode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户相关Mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<UserMode> {

    //获取根目录
    @Select("select FileID from usertable where ID=#{ID} limit 1")
    Integer getRootUrlId(Integer ID);
}
