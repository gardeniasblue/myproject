package com.blogmang.controller.admin;

import com.blogmang.constants.HttpStatusConstants;
import com.blogmang.dto.AjaxResultPage;
import com.blogmang.dto.Result;
import com.blogmang.entity.BlogComment;
import com.blogmang.service.BlogCommentService;
import com.blogmang.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CommentController {
    @Autowired
    private BlogCommentService blogCommentService;

    @ResponseBody
    @RequestMapping("/v5/delcomment")
    public Result delComment(@RequestParam("commentId") Long commentId){
        boolean result = blogCommentService.delComByCommentId(commentId);
        if(result){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.SUCCESS);

        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);

    }
    @ResponseBody
    @RequestMapping("/v5/searchcomment")
    public AjaxResultPage<BlogComment> searchComment(@RequestParam("blogId") Long blogId){

        List<BlogComment> commentlist = blogCommentService.queryComByBlogId(blogId);
        AjaxResultPage<BlogComment> resultPage = new AjaxResultPage<>();
        resultPage.setCode(0);
        resultPage.setCount(1000);
        resultPage.setData(commentlist);


        return resultPage;
    }
    @ResponseBody
    @RequestMapping("/v5/searchallcomment")
    public AjaxResultPage<BlogComment> searchAllComment(){

        List<BlogComment> commentlist = blogCommentService.queryAllCommente();
        AjaxResultPage<BlogComment> resultPage = new AjaxResultPage<>();
        resultPage.setCode(0);
        resultPage.setCount(1000);
        resultPage.setData(commentlist);

        return resultPage;
    }
    @ResponseBody
    @RequestMapping("/v5/updatecommentstatus")
    public Result commentStatus(BlogComment blogComment){
        boolean result = blogCommentService.updateCommentStatus(blogComment);
        if(result){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.SUCCESS);

        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);

    }
    @ResponseBody
    @RequestMapping("/v5/updatecommentisdel")
    public Result commentIsDel(BlogComment blogComment){
        boolean result = blogCommentService.updateCommentIsDel(blogComment);
        if(result){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.SUCCESS);

        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);

    }

    /**
     * 评论列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/v5/commentlist")
    public AjaxResultPage<BlogComment> commentList(){

        List<BlogComment> commentlist = blogCommentService.queryCommentenable(0);
        AjaxResultPage<BlogComment> resultPage = new AjaxResultPage<>();
        resultPage.setCode(0);
        resultPage.setCount(1000);
        resultPage.setData(commentlist);

        return resultPage;
    }
}
