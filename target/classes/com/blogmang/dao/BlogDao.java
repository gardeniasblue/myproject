package com.blogmang.dao;


import com.blogmang.entity.BlogComment;
import com.blogmang.entity.BlogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {
    List<BlogInfo> queryAllBlog();
    boolean saveBlog(BlogInfo blogInfo);
    List<BlogInfo > selectBlogByNew();
    List<BlogInfo> selectBlogByClick();
    BlogInfo queryBlogById(Long blogId);
    List<BlogInfo> selectBlogByBlogTitle(String blogTitle);
    boolean blogDelete(Long blogId);
    boolean updateBlog(BlogInfo blogInfo);
    boolean updateBlogStatus(BlogInfo blogInfo);
    boolean updateBlogenableComment(BlogInfo blogInfo);
    List<BlogInfo> searchBlog(BlogInfo blogInfo);
    List<BlogInfo> queryAllBlogView();
    List<BlogInfo> queryBlogByCatId(Integer blogCategoryId);
    boolean updateBlogCat(BlogInfo blogInfo);
    List<BlogInfo> queryBlogByTags(String blogTags);
    boolean updateBlogTags(BlogInfo blogInfo);
    Integer countBlog();
}
