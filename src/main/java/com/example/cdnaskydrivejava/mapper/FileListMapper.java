package com.example.cdnaskydrivejava.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cdnaskydrivejava.model.FileTableDataMode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 文件列表Mapper
 */
@Mapper
public interface FileListMapper extends BaseMapper<FileTableDataMode> {
    //获取某个菜单下属的内容
    @Select("SELECT * FROM userfiletable where UP=#{dirId};")
    List<FileTableDataMode> SelectByDir(Integer dirId);

    //检查一个用户是否拥有一个目录/文件
    @Select("SELECT count(*) FROM userfiletable where ID = #{dirId} and userId=#{userId};")
    Integer DirOfUser(Integer dirId,Integer userId);

    //通过hash取得文件路径
    @Select("select FilePath from hashtable where Hash = #{hashStr}")
    String SelectFileByFileID(String hashStr);
}
