package com.blogmang.controller.admin;

import com.blogmang.constants.HttpStatusConstants;
import com.blogmang.dto.AjaxResultPage;
import com.blogmang.dto.Result;
import com.blogmang.entity.BlogInfo;
import com.blogmang.entity.BlogTag;
import com.blogmang.entity.BlogTagRelation;
import com.blogmang.service.BlogService;
import com.blogmang.service.BlogTagRelationService;
import com.blogmang.service.BlogTagService;
import com.blogmang.util.DateUtils;
import com.blogmang.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private BlogTagService blogTagService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogTagRelationService blogTagRelationService;

    @RequestMapping("/until/tags/list")
    @ResponseBody
    public Result<BlogTag> tagsList(){
        List<BlogTag> list = blogTagService.queryAllTags();
        if (CollectionUtils.isEmpty(list)){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR,"服务器异常");
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,list);
    }

    @ResponseBody
    @RequestMapping("/v4/taglistview")
    public AjaxResultPage<BlogTag> tagsListVo(){

        List<BlogTag> tagList = blogTagService.queryAllTagsVO();
        AjaxResultPage<BlogTag> resultPage = new AjaxResultPage<>();
        resultPage.setCode(0);
        resultPage.setCount(1000);
        resultPage.setData(tagList);


        return resultPage;
    }
    @RequestMapping("/v4/tagsadd")
    @ResponseBody
    public Result<BlogTag> tagsAdd(BlogTag blogTag){

        blogTag.setCreateTime(DateUtils.getLocalCurrentDate());
        boolean saveresult = blogTagService.saveTags(blogTag);
        if (saveresult){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,"成功");
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR,"服务器异常");
    }
    @RequestMapping("/v4/tagsupdate")
    @ResponseBody
    public Result<BlogTag> tagsUpdate(BlogTag blogTag){
        boolean updateresult = blogTagService.updateTagIsDel(blogTag);
        if (updateresult){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,"成功");
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR,"服务器异常");
    }
    @RequestMapping("/v4/tagsdel")
    @ResponseBody
    public Result<BlogTag> tagsDel(Integer tagId){

        BlogTag tag = blogTagService.queryTagByTagId(tagId);
        blogTagService.tagDel(tag.getTagId());
        List<BlogInfo> blogInfoList = blogService.queryBlogByTags(tag.getTagName());
        for(BlogInfo blogInfo : blogInfoList){
            blogInfo.setBlogTags("默认标签");
            blogService.updateBlogTags(blogInfo);
        }
        List<BlogTagRelation> blogTagRelations = blogTagRelationService.queryTagsByTagId(tagId);
        for(BlogTagRelation blogTagRelation : blogTagRelations){
            blogTagRelation.setTagId(1);
            blogTagRelationService.updateTagRealByDelTag(blogTagRelation);
        }

        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,"成功");

    }
}
