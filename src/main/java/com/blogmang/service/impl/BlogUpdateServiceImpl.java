package com.blogmang.service.impl;

import com.blogmang.dao.BlogUpdateDao;
import com.blogmang.dao.SystemUpdateDao;
import com.blogmang.entity.BlogUpdate;
import com.blogmang.entity.SystemUpdate;
import com.blogmang.service.BlogUpdateService;
import com.blogmang.service.SystemUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogUpdateServiceImpl implements BlogUpdateService {

    @Autowired
    private BlogUpdateDao blogUpdateDao;


    @Override
    public List<BlogUpdate> queryAllBlogUpdate() {
        return blogUpdateDao.queryAllBlogUpdate();
    }

    @Override
    public boolean addBlogUpdate(BlogUpdate blogUpdateUpdate) {
        blogUpdateDao.addBlogUpdate(blogUpdateUpdate);
        return true;
    }
}
