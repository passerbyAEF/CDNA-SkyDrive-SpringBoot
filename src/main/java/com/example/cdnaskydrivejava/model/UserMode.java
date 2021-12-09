package com.example.cdnaskydrivejava.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@TableName("usertable")
public class UserMode {
    @TableId("ID")
    Integer id;
    @JsonProperty("Name")
    @TableField("UserName")
    String name;
    @JsonProperty("Pwds")
    @TableField("PassWord")
    String pwd;
    @TableField("FileID")
    Integer fileId;
}
