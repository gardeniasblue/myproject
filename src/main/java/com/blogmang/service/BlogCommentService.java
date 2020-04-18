package com.blogmang.service;

import com.blogmang.entity.BlogComment;

import java.util.List;

public interface BlogCommentService {
    List<BlogComment> queryComByBlogId(Long blogId);
    boolean saveComment(BlogComment blogComment);
    Integer countComment(Long blogId);
    List<BlogComment> queryCommentenable(Integer commentStatus);
    boolean delComByBlogId(Long blogId);
    List<BlogComment> queryAllCommente();
    boolean delComByCommentId(Long commentId);
    boolean updateCommentStatus(BlogComment blogComment);
    boolean updateCommentIsDel(BlogComment blogComment);
    List<BlogComment> queryComByBlog(BlogComment blogComment);
    Integer countCommentSy();
    Integer countCommentSn();
}
