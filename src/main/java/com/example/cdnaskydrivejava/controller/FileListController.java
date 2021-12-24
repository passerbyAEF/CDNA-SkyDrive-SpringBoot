package com.example.cdnaskydrivejava.controller;

import com.example.cdnaskydrivejava.model.UserMode;
import com.example.cdnaskydrivejava.service.FileListService;
import com.example.cdnaskydrivejava.service.UserService;
import com.example.cdnaskydrivejava.util.BaseController;
import com.example.cdnaskydrivejava.util.ReturnMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 关于文件列表的Controller
 */
@ResponseBody
@Controller
@RequestMapping("api")
public class FileListController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    FileListService fileListService;

    /*
    文件列表响应格式为：
    [
        {
            type:"dir"/"file",
            name:"名字",
            time:"修改时间",
            size(如果是文件):"1024MB"
        },
        {
            type:"dir"/"file",
            name:"名字",
            time:"修改时间",
            size(如果是文件):"1024MB"
        },
        ....
    ]
     */

    @PostMapping("list")
    public ReturnMode<Object> getList(@RequestBody Map<String, Integer> map) {
        UserMode user = (UserMode) getUser().getPrincipal();
        if (!map.containsKey("url")) {
            return Error("参数错误");
        }
        Integer dirId = map.get("url");
        if (fileListService.isDirOfUser(map.get("url"), user.getId())) {
            return OK(fileListService.findOfDir(dirId));
        }
        return Error("用户权限错误");
    }

    @GetMapping("rooturl")
    public ReturnMode<Object> getRootUrl() {
        UserMode user = (UserMode) getUser().getPrincipal();
        return OK(userService.getRootUrlId(user));
    }
}
