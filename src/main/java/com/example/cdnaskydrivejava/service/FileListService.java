package com.example.cdnaskydrivejava.service;

import java.util.List;

public interface FileListService {

    Boolean isDirOfUser(Integer dirId, Integer userid);

    List<Object> findOfDir(Integer dirId);

    Boolean addDir(String name,Integer parentsDir, Integer userid);

    Integer toBackDir(Integer dirId);
}
