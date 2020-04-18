package com.blogmang.dao;

import com.blogmang.entity.BlogComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogCommentDao {
    List<BlogComment> queryComByBlogId(Long blogId);
    boolean saveComment(BlogComment blogComment);
    Integer countComment(Long blogId);
    List<BlogComment> queryCommentenable(Integer commentStatus);
    boolean delComByBlogId(Long blogId);
    boolean delComByCommentId(Long commentId);

    List<BlogComment> queryAllCommente();
    boolean updateCommentStatus(BlogComment blogComment);
    boolean updateCommentIsDel(BlogComment blogComment);
    List<BlogComment> queryComByBlog(BlogComment blogComment);
    Integer countCommentSy();
    Integer countCommentSn();
}
