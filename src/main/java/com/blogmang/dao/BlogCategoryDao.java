package com.blogmang.dao;

import com.blogmang.entity.BlogCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogCategoryDao {
    List<BlogCategory> queryAllCategory();
    List<BlogCategory> queryCateVo();
    boolean saveCategory(BlogCategory blogCategory);
    boolean updateCatIsDel(BlogCategory blogCategory);
    boolean delCategory(Integer categoryId);
}
