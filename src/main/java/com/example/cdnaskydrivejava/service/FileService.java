package com.example.cdnaskydrivejava.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    boolean addFile(MultipartFile file,Integer dirId, Integer userId);
}
