package com.blogmang.dao;

import com.blogmang.entity.BlogTagRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface BlogTagRelationDao {
    List<BlogTagRelation> queryTagsByBlogId(Long blogId);
    boolean addBlogTagRelation(BlogTagRelation blogTagRelation);
    List<BlogTagRelation> queryTagsByTagId(Integer tagId);
    boolean deleteByBlogId(Long blogId);
    boolean updateTagRealByDelTag(BlogTagRelation blogTagRelation);
}
