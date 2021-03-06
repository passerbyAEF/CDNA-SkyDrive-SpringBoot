package com.example.cdnaskydrivejava.service;

import java.util.List;

public interface FileListService {

    Boolean isDirOfUser(Integer dirId, Integer userid);

    List<Object> findOfDir(Integer dirId);

    Boolean addDir(String name,Integer parentsDir, Integer userid);

    Integer toBackDir(Integer dirId);

    Boolean delete(Integer id,Integer userid);

    List<Object> findTextFile(Integer userid);

    List<Object> findPictureFile(Integer userid);

    List<Object> findMediaFile(Integer userid);
}
