package com.blogmang.service.impl;

import com.blogmang.dao.BlogDao;
import com.blogmang.entity.BlogInfo;
import com.blogmang.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;
    @Override
    public List<BlogInfo> queryAllBlog() {
        return blogDao.queryAllBlog();
    }

    @Override
    public boolean saveBlog(BlogInfo blogInfo) {
        blogDao.saveBlog(blogInfo);
        return true;
    }

    @Override
    public List<BlogInfo> selectBlogByNew() {
        return blogDao.selectBlogByNew();
    }

    @Override
    public List<BlogInfo> selectBlogByClick() {
        return blogDao.selectBlogByClick();
    }

    @Override
    public BlogInfo queryBlogById(Long blogId) {
        return blogDao.queryBlogById(blogId);
    }

    @Override
    public List<BlogInfo> selectBlogByBlogTitle(String blogTitle) {
        return blogDao.selectBlogByBlogTitle(blogTitle);
    }

    @Override
    public boolean blogDelete(Long blogId) {
        blogDao.blogDelete(blogId);
        return true;
    }

    @Override
    public boolean updateBlog(BlogInfo blogInfo) {
        blogDao.updateBlog(blogInfo);
        return true;
    }

    @Override
    public boolean updateBlogStatus(BlogInfo blogInfo) {
        blogDao.updateBlogStatus(blogInfo);
        return true;
    }

    @Override
    public boolean updateBlogenableComment(BlogInfo blogInfo) {
        blogDao.updateBlogenableComment(blogInfo);
        return true;
    }

    @Override
    public List<BlogInfo> searchBlog(BlogInfo blogInfo) {
        return blogDao.searchBlog(blogInfo);
    }

    @Override
    public List<BlogInfo> queryAllBlogView() {
        return blogDao.queryAllBlogView();
    }

    @Override
    public List<BlogInfo> queryBlogByCatId(Integer blogCategoryId) {
        return blogDao.queryBlogByCatId(blogCategoryId);
    }

    @Override
    public boolean updateBlogCat(BlogInfo blogInfo) {
        blogDao.updateBlogCat(blogInfo);
        return true;
    }

    @Override
    public List<BlogInfo> queryBlogByTags(String blogTags) {
        return blogDao.queryBlogByTags(blogTags);
    }

    @Override
    public boolean updateBlogTags(BlogInfo blogInfo) {
        blogDao.updateBlogTags(blogInfo);
        return true;
    }

    @Override
    public Integer countBlog() {
        return blogDao.countBlog();
    }
}
