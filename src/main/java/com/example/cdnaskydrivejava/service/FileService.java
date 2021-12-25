package com.example.cdnaskydrivejava.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {
    Integer addFile(MultipartFile file,Integer dirId, Integer userId);

    void loadFile(HttpServletResponse response,Integer fileId, Integer userId) throws IOException;
}
