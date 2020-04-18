package com.blogmang.service;

import com.blogmang.entity.BlogTag;

import java.util.List;

public interface BlogTagService {
    List<BlogTag> queryAllTags();
    List<BlogTag> queryAllTagsVO();

    BlogTag queryTagByTagId(Integer tagId);
    BlogTag queryTagByTagName(String tagName);
    boolean saveTags(BlogTag blogTag);
    boolean updateTagIsDel(BlogTag blogTag);
    boolean tagDel(Integer tagId);

}
