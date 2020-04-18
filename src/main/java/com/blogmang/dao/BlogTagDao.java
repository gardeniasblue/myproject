package com.blogmang.dao;

import com.blogmang.entity.BlogTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogTagDao {
    List<BlogTag> queryAllTags();
    List<BlogTag> queryAllTagsVO();
    BlogTag queryTagByTagId(Integer tagId);
    BlogTag queryTagByTagName(String tagName);
    boolean saveTags(BlogTag blogTag);
    boolean updateTagIsDel(BlogTag blogTag);
    boolean tagDel(Integer tagId);

}
