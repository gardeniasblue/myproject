package com.blogmang.service.impl;

import com.blogmang.dao.BlogCategoryDao;
import com.blogmang.entity.BlogCategory;
import com.blogmang.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private BlogCategoryDao blogCategoryDao;
    @Override
    public List<BlogCategory> queryAllCategory() {
        return blogCategoryDao.queryAllCategory();
    }

    @Override
    public List<BlogCategory> queryCateVo() {
        return blogCategoryDao.queryCateVo();
    }

    @Override
    public boolean saveCategory(BlogCategory blogCategory) {
        blogCategoryDao.saveCategory(blogCategory);
        return true;
    }

    @Override
    public boolean updateCatIsDel(BlogCategory blogCategory) {
        blogCategoryDao.updateCatIsDel(blogCategory);
        return true;
    }

    @Override
    public boolean delCategory(Integer categoryId) {
        blogCategoryDao.delCategory(categoryId);
        return true;
    }
}
