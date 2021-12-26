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
import java.util.Date;
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

    @Override
    public Boolean addDir(String name, Integer parentsDir, Integer userId) {
        if (!isDirOfUser(parentsDir, userId)) {
            return false;
        }
        FileTableDataMode mode = new FileTableDataMode();
        mode.setUserId(userId);
        mode.setTime(new Date());
        mode.setUP(parentsDir);
        mode.setName(name);
        mode.setState(1);
        fileListMapper.insert(mode);
        return true;
    }

    @Override
    public Integer toBackDir(Integer dirId) {
        FileTableDataMode mode = fileListMapper.selectById(dirId);
        return mode.getUP();
    }

    @Override
    public Boolean delete(Integer id, Integer userid) {
        FileTableDataMode mode = fileListMapper.selectById(id);
        if (!mode.getUserId().equals(userid)) {
            return false;
        }
        if (mode.isDir()) {
            deleteDir(id);
        }else{
            deleteFile(id);
        }
        return true;
    }

    void deleteFile(Integer id) {
        fileListMapper.deleteById(id);
    }

    void deleteDir(Integer id) {
        List<FileTableDataMode> list=fileListMapper.SelectByDir(id);
        for (FileTableDataMode mode : list) {
            if (mode.isDir()) {
                deleteDir(mode.getId());
            } else {
                deleteFile(mode.getId());
            }
        }
        fileListMapper.deleteById(id);
    }
}
