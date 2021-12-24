package com.example.cdnaskydrivejava.controller;

import com.example.cdnaskydrivejava.util.BaseController;
import com.example.cdnaskydrivejava.util.ReturnMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@ResponseBody
@Controller
@RequestMapping("api/Load/")
public class FileController extends BaseController {

    @PostMapping("Up")
    public ReturnMode<Object> fileUp(@RequestParam Map<String, MultipartFile> file) {

        return OK("");
    }

}
