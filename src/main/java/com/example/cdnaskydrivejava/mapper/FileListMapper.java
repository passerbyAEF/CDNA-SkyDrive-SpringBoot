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
    @Select("SELECT * FROM filetable where UP=#{dirId};")
    List<FileTableDataMode> SelectByDir(Integer dirId);

    //检查一个用户是否拥有一个目录/文件
    @Select("SELECT count(*) FROM filetable where ID = #{dirId} and userId=#{userId};")
    Integer DirOfUser(Integer dirId, Integer userId);

    //通过hash取得文件路径
    @Select("select FilePath from hashtable where Hash = #{hashStr}")
    String SelectFileByFileID(String hashStr);

    //检索一个用户拥有的文本文件(.txt .docx .doc .xml .ppt .xls .xlsx .wps .html .pdf)
    @Select("SELECT * FROM filetable where userId=#{userId} and (" +
            "name like '%.txt' or " +
            "name like '%.docx' or " +
            "name like '%.doc' or " +
            "name like '%.xml' or " +
            "name like '%.ppt' or " +
            "name like '%.xls' or " +
            "name like '%.xlsx' or " +
            "name like '%.wps' or " +
            "name like '%.html' or " +
            "name like '%.pdf' )")
    List<FileTableDataMode> SelectText(Integer userId);

    //检索一个用户拥有的图片文件(.jpg .png .gif .psd .ppd .bmp)
    @Select("SELECT * FROM filetable where userId=#{userId} and (" +
            "name like '%.jpg' or " +
            "name like '%.png' or " +
            "name like '%.gif' or " +
            "name like '%.psd' or " +
            "name like '%.ppd' or " +
            "name like '%.bmp' )" +
            ";")
    List<FileTableDataMode> SelectPicture(Integer userId);

    //检索一个用户拥有的视频文件(.avi .rmvb .rm .mp4 .flv .mpg)
    @Select("SELECT * FROM filetable where userId=#{userId} and (" +
            "name like '%.mp4' or " +
            "name like '%.avi' or " +
            "name like '%.rmvb' or " +
            "name like '%.rm' or " +
            "name like '%.flv' or " +
            "name like '%.mpg' )" +
            ";")
    List<FileTableDataMode> SelectMedia(Integer userId);
}
