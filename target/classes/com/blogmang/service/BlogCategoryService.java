package com.blogmang.service;

import com.blogmang.entity.BlogCategory;

import java.util.List;

public interface BlogCategoryService {
    List<BlogCategory> queryAllCategory();
    List<BlogCategory> queryCateVo();
    boolean saveCategory(BlogCategory blogCategory);
    boolean updateCatIsDel(BlogCategory blogCategory);
    boolean delCategory(Integer categoryId);
}
