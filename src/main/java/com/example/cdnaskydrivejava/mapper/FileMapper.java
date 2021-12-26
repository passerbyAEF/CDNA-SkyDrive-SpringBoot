package com.example.cdnaskydrivejava.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 获取文件Mapper
 */
@Mapper
public interface FileMapper  {
    //通过hash获取文件路径
    @Select("select FilePath from hashtable where Hash=#{hash}")
    String findPathByHash(String hash);

    //添加文件
    @Insert("insert into hashtable values (#{hash},#{path});")
    Integer insertFile(String hash,String path);
}
