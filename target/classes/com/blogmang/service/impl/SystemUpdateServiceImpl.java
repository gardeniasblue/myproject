package com.blogmang.service.impl;

import com.blogmang.dao.SystemUpdateDao;
import com.blogmang.entity.SystemUpdate;
import com.blogmang.service.SystemUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SystemUpdateServiceImpl implements SystemUpdateService {

    @Autowired
    private SystemUpdateDao systemUpdateDao;
    @Override
    public List<SystemUpdate> queryAllSystemUpdate() {
        return systemUpdateDao.queryAllSystemUpdate();
    }

    @Override
    public boolean addSystemUpdate(SystemUpdate systemUpdate) {
        systemUpdateDao.addSystemUpdate(systemUpdate);
        return true;
    }

    @Override
    public String selectSystemVersion() {
        return systemUpdateDao.selectSystemVersion();
    }
}
