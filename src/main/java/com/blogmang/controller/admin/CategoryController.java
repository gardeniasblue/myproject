package com.blogmang.controller.admin;

import com.blogmang.constants.HttpStatusConstants;
import com.blogmang.dto.AjaxResultPage;
import com.blogmang.dto.Result;
import com.blogmang.entity.BlogCategory;
import com.blogmang.entity.BlogInfo;
import com.blogmang.service.BlogCategoryService;
import com.blogmang.service.BlogService;
import com.blogmang.util.DateUtils;
import com.blogmang.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private BlogCategoryService blogCategoryService;
    @Autowired
    private BlogService blogService;

    @ResponseBody
    @GetMapping("/v3/categorylist")
    public Result<BlogCategory> categoryList(){

        List<BlogCategory> list = blogCategoryService.queryAllCategory();
        if (CollectionUtils.isEmpty(list)){
            ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,list);
    }

    @ResponseBody
    @RequestMapping("/v3/categorylistview")
    public AjaxResultPage<BlogCategory> categoryListVo(){

        List<BlogCategory> blogCategoryList = blogCategoryService.queryCateVo();
        AjaxResultPage<BlogCategory> resultPage = new AjaxResultPage<>();
        resultPage.setCode(0);
        resultPage.setCount(1000);
        resultPage.setData(blogCategoryList);

        return resultPage;
    }
    @ResponseBody
    @PostMapping("/v3/categoryadd")
    public Result<BlogCategory> categoryAdd(BlogCategory blogCategory){
        blogCategory.setCreateTime(DateUtils.getLocalCurrentDate());

        Boolean result = blogCategoryService.saveCategory(blogCategory);
        if (result){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,"成功");
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR,"服务器异常");
    }

    /**
     * 删除分类时，同时根据分类id查询当前分类对应的博客，同时，将这些博客的分类修改为默认
     * @param categoryId
     * @return Result
     */
    @ResponseBody
    @PostMapping("/v3/categorydel")
    public Result<BlogCategory> categoryDel(Integer categoryId){
        List<BlogInfo> blogInfoList = blogService.queryBlogByCatId(categoryId);
        for(BlogInfo blogInfo : blogInfoList){
            blogInfo.setBlogCategoryId(1);
            blogInfo.setBlogCategoryName("默认分类");
            blogService.updateBlogCat(blogInfo);
        }
        boolean delresult = blogCategoryService.delCategory(categoryId);
        if (delresult){

            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,"成功");
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR,"服务器异常");
    }
    @ResponseBody
    @PostMapping("/v3/categoryupdate")
    public Result<BlogCategory> categoryUpdate(BlogCategory blogCategory){

        Boolean result = blogCategoryService.updateCatIsDel(blogCategory);
        if (result){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,"成功");
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR,"服务器异常");
    }
}
