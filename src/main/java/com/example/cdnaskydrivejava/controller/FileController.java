package com.example.cdnaskydrivejava.controller;

import com.example.cdnaskydrivejava.model.UserMode;
import com.example.cdnaskydrivejava.service.FileService;
import com.example.cdnaskydrivejava.util.BaseController;
import com.example.cdnaskydrivejava.util.ReturnMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ResponseBody
@Controller
@RequestMapping("api/Load/")
public class FileController extends BaseController {

    @Autowired
    FileService fileService;


    @PostMapping("Up")
    public ReturnMode<Object> fileUp(HttpServletRequest request, @RequestParam Map<String, MultipartFile> file) {
        Integer dirId = Integer.parseInt(request.getHeader("Path"));
        UserMode user = (UserMode) getUser().getPrincipal();
        int num = 0;
        for (MultipartFile multipartFile : file.values()) {
            if (fileService.addFile(multipartFile, dirId,user.getId()))
                num++;
        }
        return OK("你成功上传" + num + "个");
    }

}
