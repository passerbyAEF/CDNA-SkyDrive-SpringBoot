package com.example.cdnaskydrivejava.controller;

import com.example.cdnaskydrivejava.model.UserMode;
import com.example.cdnaskydrivejava.service.FileService;
import com.example.cdnaskydrivejava.util.BaseController;
import com.example.cdnaskydrivejava.util.ReturnMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ResponseBody
@Controller
@RequestMapping("api/Load/")
public class FileController extends BaseController {

    @Autowired
    FileService fileService;


    @PostMapping("Up")
    public ReturnMode<Object> fileUpLoad(HttpServletRequest request, @RequestParam Map<String, MultipartFile> file) {
        Integer dirId = Integer.parseInt(request.getHeader("Path"));
        UserMode user = (UserMode) getUser().getPrincipal();
        List<Integer> list = new ArrayList<>();
        for (MultipartFile multipartFile : file.values()) {
            Integer i = fileService.addFile(multipartFile, dirId, user.getId());
            if (i != -1)
                list.add(i);
        }
        return OK(list);
    }

    @GetMapping("Down")
    public void fileDownLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer fileId = Integer.parseInt(request.getHeader("File"));
        UserMode user = (UserMode) getUser().getPrincipal();
        fileService.loadFile(response,fileId,user.getId());
    }

}
