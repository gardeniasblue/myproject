package com.blogmang.service.impl;

import com.blogmang.dao.BlogTagRelationDao;
import com.blogmang.entity.BlogTagRelation;
import com.blogmang.service.BlogTagRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogTagRelationServiceImpl implements BlogTagRelationService {
    @Autowired
    private BlogTagRelationDao blogTagRelationDao;
    @Override
    public List<BlogTagRelation> queryTagsByBlogId(Long blogId) {
        return blogTagRelationDao.queryTagsByBlogId(blogId);
    }

    @Override
    public boolean addBlogTagRelation(BlogTagRelation blogTagRelation) {
        blogTagRelationDao.addBlogTagRelation(blogTagRelation);
        return true;
    }

    @Override
    public List<BlogTagRelation> queryTagsByTagId(Integer tagId) {
        return blogTagRelationDao.queryTagsByTagId(tagId);
    }

    @Override
    public boolean deleteByBlogId(Long blogId) {
        blogTagRelationDao.deleteByBlogId(blogId);
        return true;
    }

    @Override
    public boolean updateTagRealByDelTag(BlogTagRelation blogTagRelation) {
        blogTagRelationDao.updateTagRealByDelTag(blogTagRelation);
        return true;
    }


}
