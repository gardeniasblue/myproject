package com.blogmang.controller.admin;

import com.blogmang.constants.HttpStatusConstants;
import com.blogmang.dto.Result;
import com.blogmang.entity.BlogUpdate;
import com.blogmang.entity.SystemUpdate;
import com.blogmang.service.BlogUpdateService;
import com.blogmang.service.SystemUpdateService;
import com.blogmang.util.DateUtils;
import com.blogmang.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogUpdateController {
    @Autowired
    private BlogUpdateService blogUpdateService;
    @RequestMapping("/v9/queryblogupdateall")
    @ResponseBody
    public Result<BlogUpdate> blogupdate(){
        List<BlogUpdate> list = blogUpdateService.queryAllBlogUpdate();
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,list);
    }
    @RequestMapping("/v8/addblogupdate")
    @ResponseBody
    public Result<BlogUpdate> addblogupdate(BlogUpdate blogUpdate){
        blogUpdate.setCreateTime(DateUtils.getLocalCurrentDate());
        boolean result = blogUpdateService.addBlogUpdate(blogUpdate);
        if(result){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,"成功");
        }else{
            return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR,"失败");
        }

    }

}
