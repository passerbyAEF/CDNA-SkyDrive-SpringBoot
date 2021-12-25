package com.example.cdnaskydrivejava.service.impl;

import com.example.cdnaskydrivejava.mapper.FileListMapper;
import com.example.cdnaskydrivejava.model.DirMode;
import com.example.cdnaskydrivejava.model.FileMode;
import com.example.cdnaskydrivejava.model.FileTableDataMode;
import com.example.cdnaskydrivejava.service.FileListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileListServiceImpl implements FileListService {

    @Autowired
    FileListMapper fileListMapper;

    @Override
    public Boolean isDirOfUser(Integer dirId, Integer userId) {
        return fileListMapper.DirOfUser(dirId, userId) > 0;
    }

    @Override
    public List<Object> findOfDir(Integer dirId) {
        List<FileTableDataMode> list = fileListMapper.SelectByDir(dirId);
        List<Object> datalist = new ArrayList<>();
        for (FileTableDataMode l : list) {
            if (l.isDir()) {
                datalist.add(new DirMode(l));
            } else {
                File file = new File(fileListMapper.SelectFileByFileID(l.getValue()));
                datalist.add(new FileMode(l, file.length()));
            }
        }
        return datalist;
    }
}
