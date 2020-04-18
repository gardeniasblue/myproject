package com.blogmang.controller.blog;

import com.blogmang.constants.HttpStatusConstants;
import com.blogmang.controller.vo.BlogDetailVO;
import com.blogmang.dto.AjaxResultPage;
import com.blogmang.dto.Result;
import com.blogmang.entity.*;
import com.blogmang.service.*;
import com.blogmang.util.DateUtils;
import com.blogmang.util.ResultGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/view")
public class BlogVoController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogCommentService blogCommentService;
    @Autowired
    private BlogTagRelationService blogTagRelationService;
    @Autowired
    private BlogTagService blogTagService;
    @Autowired
    private AboutMeService aboutMeService;
    @Autowired
    private LeaveMessageService leaveMessageService;


    /**
     * 根据博客id查询 返回至博客详情页
     * @param model
     * @param blogId
     * @return detail
     */
    @RequestMapping("/v2/bloginfo/{blogId}")
    public String toBlogInfo(Model model, @PathVariable("blogId") Long blogId){


        BlogInfo blogInfo = blogService.queryBlogById(blogId);
        List<BlogComment> blogCommentList = blogCommentService.queryComByBlogId(blogId);
        List<BlogTag> blogtaglist = new ArrayList();
        //通过博客id查询标签关系表，得到一个标签id数组,通过这个数组，去标签库查询标签名
        List<BlogTagRelation> blogTagRelations = blogTagRelationService.queryTagsByBlogId(blogId);
        for(BlogTagRelation blogtagid : blogTagRelations){

            BlogTag blogTag = blogTagService.queryTagByTagId(blogtagid.getTagId());
            blogtaglist.add(blogTag);
        }
        BlogDetailVO blogDetailVO = new BlogDetailVO();
        BeanUtils.copyProperties(blogInfo,blogDetailVO);
        Integer cont = blogCommentService.countComment(blogId);
        blogDetailVO.setCommentCount(cont);

        model.addAttribute("blogDetailVO",blogDetailVO);
        model.addAttribute("bloginfo",blogInfo);
        model.addAttribute("blogcomment",blogCommentList);
        model.addAttribute("blogtaglist",blogtaglist);

        return "blog/amaze/detail";
    }
    @RequestMapping("/v2/search/{keyword}")
    public String search(@PathVariable("keyword")String keyword , Model model){
        List<BlogInfo> searchlist = blogService.selectBlogByBlogTitle(keyword);
        List<BlogTag> blogTagList = blogTagService.queryAllTags();
        List<BlogInfo> newsBlog = blogService.selectBlogByNew();
        List<BlogInfo> blogViews = blogService.selectBlogByClick();

        model.addAttribute("blogtaglist",blogTagList);
        model.addAttribute("newsblog",newsBlog);
        model.addAttribute("blogviews",blogViews);
        model.addAttribute("searchlist",searchlist);
        return "blog/amaze/search-list";
    }
    /**
     * 根据标签查找博客
     * @param tagId
     * @param model
     * @return
     */
    @RequestMapping("/v2/tag/bloglist/{tagId}")
    public String blogListByTag(@PathVariable("tagId")Integer tagId ,Model model){

        //通过标签id去标签数据库查询对应的博客id list
        List<BlogTagRelation> blogidlist = blogTagRelationService.queryTagsByTagId(tagId);
        //遍历博客id list 去博客数据库查询对应的博客，放入list


        List<BlogInfo> blogInfoList = new ArrayList<>();
        for(BlogTagRelation blogTagRelations  : blogidlist){
            BlogInfo blogInfo =  blogService.queryBlogById(blogTagRelations.getBlogId());
            if(blogInfo!=null){
                blogInfoList.add(blogInfo);
            }
        }
        List<BlogTag> blogTagList = blogTagService.queryAllTags();
        List<BlogInfo> newsBlog = blogService.selectBlogByNew();
        List<BlogInfo> blogViews = blogService.selectBlogByClick();
        model.addAttribute("blogtaglist",blogTagList);
        model.addAttribute("newsblog",newsBlog);
        model.addAttribute("blogviews",blogViews);
        model.addAttribute("tagsearchlist",blogInfoList);

        return "blog/amaze/tagsearch-list";

    }

    /**
     * 关于我
     * @param model
     * @return
     */
    @RequestMapping("/v2/queryaboutme")
    public String queryAboutMe(Model model){
        AboutMe aboutMeList = aboutMeService.queryAboutMe();
        model.addAttribute("aboutlist",aboutMeList);
        return "blog/amaze/aboutme";
    }

    /**
     * 博客关联的评论列表
     * @param blogComment
     * @return
     */
    @GetMapping("/v5/listComment")
    @ResponseBody
    public AjaxResultPage<BlogComment> listComment(BlogComment blogComment){
        List<BlogComment> commentList = blogCommentService.queryComByBlog(blogComment);
        AjaxResultPage<BlogComment> ajaxResultPage = new AjaxResultPage<>();
        //ajaxResultPage.setCount(page.getTotal());
        //ajaxResultPage.setData(page.getRecords());
        ajaxResultPage.setData(commentList);

        return ajaxResultPage;
    }

    /**
     * 添加评论
     * @param request
     * @param blogComment
     * @return
     */
    @PostMapping(value = "/v5/comment")
    @ResponseBody
    public Result comment(HttpServletRequest request, @Valid BlogComment blogComment) {
        String ref = request.getHeader("Referer");
        if (StringUtils.isEmpty(ref)) {
            return ResultGenerator.genFailResult("非法请求");
        }

        blogComment.setCommentCreateTime(DateUtils.getLocalCurrentDate());
        boolean result = blogCommentService.saveComment(blogComment);
        if(result){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
        }else {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR,"服务器遇到错误");

        }
    }
    @RequestMapping("/v10/savemessage")
    @ResponseBody
    public Result saveMessage(LeaveMessage leaveMessage){
        leaveMessage.setLeaveMessageCreateTime(DateUtils.getLocalCurrentDate());
        leaveMessageService.saveMessage(leaveMessage);
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
    }

}
