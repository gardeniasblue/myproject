package com.blogmang.service;

import com.blogmang.entity.BlogTagRelation;

import java.util.List;

public interface BlogTagRelationService {
    List<BlogTagRelation> queryTagsByBlogId(Long blogId);
    boolean addBlogTagRelation(BlogTagRelation blogTagRelation);
    List<BlogTagRelation> queryTagsByTagId(Integer tagId);
    boolean deleteByBlogId(Long blogId);
    boolean updateTagRealByDelTag(BlogTagRelation blogTagRelation);

}
