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
    //获取文件夹下属的文件列表
    @PostMapping("list")
    public ReturnMode<Object> getList(@RequestBody Map<String, Integer> map) {
        UserMode user = getUser();
        if (!map.containsKey("url")) {
            return Error("参数错误");
        }
        Integer dirId = map.get("url");
        if (fileListService.isDirOfUser(map.get("url"), user.getId())) {
            return OK(fileListService.findOfDir(dirId));
        }
        return Error("用户权限错误");
    }

    //获取一个用户的根文件夹
    @GetMapping("rooturl")
    public ReturnMode<Object> getRootUrl() {
        UserMode user = getUser();
        return OK(userService.getRootUrlId(user));
    }

    //添加文件夹
    @PostMapping("AddDir")
    public ReturnMode<Object> addDir(@RequestBody Map<String, String> map) {
        UserMode user = getUser();
        if (fileListService.addDir(map.get("DirName"), Integer.parseInt(map.get("Path")), user.getId())) {
            return OK("添加成功");
        }
        return Error("用户权限错误");
    }

    //获取一个文件/文件夹的上级文件夹
    @PostMapping("BackDir")
    public ReturnMode<Object> backDir(@RequestBody Map<String, Integer> map){
        return OK(fileListService.toBackDir(map.get("Dir")));
    }

    //删除文件/文件夹
    @PostMapping("DeleteFile")
    public ReturnMode<Object> deleteFile(@RequestBody Map<String, Integer> map){
        UserMode user = getUser();
        if(fileListService.delete(map.get("FileId"),user.getId())){
            return OK("删除成功");
        }
        return Error("用户权限错误");
    }

    @GetMapping("TextList")
    public ReturnMode<Object> GetTextList (){
        UserMode user = getUser();
        return OK(fileListService.findTextFile(user.getFileId()));
    }

    @GetMapping("PictureList")
    public ReturnMode<Object> GetPictureList (){
        UserMode user = getUser();
        return OK(fileListService.findPictureFile(user.getFileId()));
    }

    @GetMapping("MediaList")
    public ReturnMode<Object> GetMediaList (){
        UserMode user = getUser();
        return OK(fileListService.findMediaFile(user.getFileId()));
    }

}
