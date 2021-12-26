package com.example.cdnaskydrivejava.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用于承载数据库表数据的实体类
 */
@Data
@TableName("filetable")
public class FileTableDataMode {
    @TableId(value = "ID",type = IdType.AUTO)
    Integer id;
    @TableField("name")
    String name;
    @TableField("Value")
    String value;
    @TableField("State")
    Integer state;
    @TableField("time")
    Date time;
    @TableField("UP")
    Integer UP;
    @TableField("userId")
    Integer userId;

    public Boolean isDir() {
        return state == 1;
    }
}
