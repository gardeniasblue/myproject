package com.blogmang.dao;

import com.blogmang.entity.BlogUpdate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogUpdateDao {
    List<BlogUpdate> queryAllBlogUpdate();
    boolean addBlogUpdate(BlogUpdate blogUpdate);
}
