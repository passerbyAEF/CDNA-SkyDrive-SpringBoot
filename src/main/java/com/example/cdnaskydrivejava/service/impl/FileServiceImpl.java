package com.example.cdnaskydrivejava.service.impl;

import com.example.cdnaskydrivejava.mapper.FileListMapper;
import com.example.cdnaskydrivejava.mapper.FileMapper;
import com.example.cdnaskydrivejava.model.FileTableDataMode;
import com.example.cdnaskydrivejava.service.FileService;
import com.example.cdnaskydrivejava.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${bufferPath}")
    String bufferPath;
    @Value("${savePath}")
    String savePath;

    @Autowired
    FileMapper fileMapper;
    @Autowired
    FileListMapper fileListMapper;

    @Override
    public boolean addFile(MultipartFile file, Integer dirId, Integer userId) {
        String bufferFileName = UUID.randomUUID().toString();
        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = new FileOutputStream(bufferPath + "\\" + bufferFileName)) {
            //先将Tomcat姐搜的文件保存到缓存文件夹内
            StreamUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            //出错把缓存删除
            File f = new File(bufferPath + "\\" + bufferFileName);
            if (f.exists()) {
                f.delete();
            }
            return false;
        }

        //计算MD5判断是否有重复
        String md5Str;
        try (InputStream inputStream = new FileInputStream(bufferPath + "\\" + bufferFileName)) {
            md5Str = MD5Util.md5HashCode32(inputStream);
        } catch (IOException e) {
            return false;
        }

        if (fileMapper.findHash(md5Str) == 0) {
            try (OutputStream outputStream = new FileOutputStream(savePath + "\\" + md5Str);
                 InputStream inputStream = new FileInputStream(bufferPath + "\\" + bufferFileName)) {
                //没保存就保存
                StreamUtils.copy(inputStream, outputStream);
                fileMapper.insertFile(md5Str, savePath + "\\" + md5Str);
            } catch (IOException e) {
                //出错把保存文件删除
                File f = new File(savePath + "\\" + md5Str);
                if (f.exists()) {
                    f.delete();
                }
                return false;
            }
        }
        File f = new File(bufferPath + "\\" + bufferFileName);
        f.delete();
        //写入文件系统
        FileTableDataMode mode = new FileTableDataMode();
        mode.setName(file.getName());
        mode.setState(0);
        mode.setTime(new Date());
        mode.setUP(dirId);
        mode.setValue(md5Str);
        mode.setUserId(userId);
        fileListMapper.insert(mode);
        return true;
    }
}
