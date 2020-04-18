package com.blogmang.service;



import com.blogmang.entity.SystemUpdate;

import java.util.List;

public interface SystemUpdateService {
    List<SystemUpdate> queryAllSystemUpdate();
    boolean addSystemUpdate(SystemUpdate systemUpdate);
    String selectSystemVersion();

}
