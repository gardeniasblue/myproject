package com.blogmang.service.impl;

import com.blogmang.dao.BlogCommentDao;
import com.blogmang.entity.BlogComment;
import com.blogmang.service.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogCommentServiceImpl implements BlogCommentService {

    @Autowired
    private BlogCommentDao blogCommentDao;
    @Override
    public List<BlogComment> queryComByBlogId(Long blogId) {
        return blogCommentDao.queryComByBlogId(blogId);
    }

    @Override
    public boolean saveComment(BlogComment blogComment) {
        blogCommentDao.saveComment(blogComment);
        return true;
    }

    @Override
    public Integer countComment(Long blogId) {
        return blogCommentDao.countComment(blogId);
    }

    @Override
    public List<BlogComment> queryCommentenable(Integer commentStatus) {
        return blogCommentDao.queryCommentenable(commentStatus);
    }

    @Override
    public boolean delComByBlogId(Long blogId) {
        blogCommentDao.delComByBlogId(blogId);
        return true;
    }

    @Override
    public List<BlogComment> queryAllCommente() {
        return blogCommentDao.queryAllCommente();
    }

    @Override
    public boolean delComByCommentId(Long commentId) {
        blogCommentDao.delComByCommentId(commentId);
        return true;
    }

    @Override
    public boolean updateCommentStatus(BlogComment blogComment) {
        blogCommentDao.updateCommentStatus(blogComment);
        return true;
    }

    @Override
    public boolean updateCommentIsDel(BlogComment blogComment) {
        blogCommentDao.updateCommentIsDel(blogComment);
        return true;
    }

    @Override
    public List<BlogComment> queryComByBlog(BlogComment blogComment) {
        return blogCommentDao.queryComByBlog(blogComment);
    }

    @Override
    public Integer countCommentSy() {
        return blogCommentDao.countCommentSy();
    }

    @Override
    public Integer countCommentSn() {
        return blogCommentDao.countCommentSn();
    }
}
