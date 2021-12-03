package com.example.cdnaskydrivejava.mapper;

import com.example.cdnaskydrivejava.model.UserMode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

//    @Select("SELECT * FROM CDNABASE.UserTable where  UserName='{user.Name}'and PassWord='{user.Pwds}';")
//    UserMode selectUser();
}
