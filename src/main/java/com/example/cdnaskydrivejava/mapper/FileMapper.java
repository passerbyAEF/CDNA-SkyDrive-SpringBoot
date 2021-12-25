package com.example.cdnaskydrivejava.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
/**
 * 获取文件Mapper
 */
@Mapper
public interface FileMapper  {
    @Select("select FilePath from hashtable where Hash=#{hash}")
    String findPathByHash(String hash);

    @Insert("insert into hashtable values (#{hash},#{path});")
    Integer insertFile(String hash,String path);
}
