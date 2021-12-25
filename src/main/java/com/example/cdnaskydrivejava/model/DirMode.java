package com.example.cdnaskydrivejava.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 表示文件夹的实体类
 */
@Data
public class DirMode {
    public DirMode(FileTableDataMode mode) {
        name = mode.getName();
        time = mode.getTime();
        id=mode.getId();
    }
    Integer id;
    String type = "dir";
    String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date time;
}
