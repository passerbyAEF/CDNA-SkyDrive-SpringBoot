package com.example.cdnaskydrivejava.model;

import lombok.Data;

import java.util.Date;

/**
 * 表示文件的实体类
 */
@Data
public class FileMode {
    public FileMode(FileTableDataMode mode, Long size) {
        name = mode.getName();
        time = mode.getTime();
        this.size = size;
    }

    String type = "file";
    String name;
    Date time;
    Long size;
}
