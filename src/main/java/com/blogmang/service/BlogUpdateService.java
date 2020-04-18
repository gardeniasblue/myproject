package com.blogmang.service;



import com.blogmang.entity.BlogUpdate;
import com.blogmang.entity.SystemUpdate;

import java.util.List;

public interface BlogUpdateService {
    List<BlogUpdate> queryAllBlogUpdate();
    boolean addBlogUpdate(BlogUpdate blogUpdateUpdate);

}
