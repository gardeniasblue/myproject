package com.blogmang.service.impl;

import com.blogmang.dao.BlogTagDao;
import com.blogmang.entity.BlogTag;
import com.blogmang.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogTagServiceImpl implements BlogTagService {

    @Autowired
    private BlogTagDao blogTagDao;

    @Override
    public List<BlogTag> queryAllTags() {
        return blogTagDao.queryAllTags();
    }

    @Override
    public List<BlogTag> queryAllTagsVO() {
        return blogTagDao.queryAllTagsVO();
    }


    @Override
    public BlogTag queryTagByTagId(Integer tagId) {
        return blogTagDao.queryTagByTagId(tagId);
    }

    @Override
    public BlogTag queryTagByTagName(String tagName) {
        return blogTagDao.queryTagByTagName(tagName);
    }

    @Override
    public boolean saveTags(BlogTag blogTag) {
        blogTagDao.saveTags(blogTag);
        return true;
    }

    @Override
    public boolean updateTagIsDel(BlogTag blogTag) {
        blogTagDao.updateTagIsDel(blogTag);
        return true;
    }

    @Override
    public boolean tagDel(Integer tagId) {
        blogTagDao.tagDel(tagId);
        return true;
    }
}
