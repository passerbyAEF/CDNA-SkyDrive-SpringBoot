package com.example.cdnaskydrivejava.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
        id = mode.getId();
        this.size = size;
    }

    String type = "file";
    Integer id;
    String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date time;
    Long size;
}
