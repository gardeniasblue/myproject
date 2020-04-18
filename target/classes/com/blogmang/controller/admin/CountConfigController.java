package com.blogmang.controller.admin;

import com.blogmang.dto.Result;
import com.blogmang.entity.LeaveMessage;
import com.blogmang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class CountConfigController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private BlogCommentService blogCommentService;
    @Autowired
    private SystemUpdateService systemUpdateService;
    @Autowired
    private LeaveMessageService leaveMessageService;

    @ResponseBody
    @GetMapping("v2/countblog")
    public Map countBlog(){
        Map countmap = new HashMap();
        Integer blogcount = blogService.countBlog();
        Integer usercount = adminUserService.countUser();
        Integer commentbycount = blogCommentService.countCommentSy();
        Integer commentbncount = blogCommentService.countCommentSn();
        countmap.put("blogcount",blogcount);
        countmap.put("usercount",usercount);
        countmap.put("commentbycount",commentbycount);
        countmap.put("commentbncount",commentbncount);
        return countmap;
    }
    @ResponseBody
    @GetMapping("v2/version")
    public String findVersion(){
       String result = systemUpdateService.selectSystemVersion();
        return result;
    }
    /**
     * 统计
     * @return
     */
    @RequestMapping("/v10/countmessage")
    @ResponseBody
    public Map countMessage(){
        Map countmap = new HashMap();
        Integer messagebycount = leaveMessageService.countMessageSy();
        Integer messagebncount = leaveMessageService.countMessageSn();

        countmap.put("messagebycount",messagebycount);
        countmap.put("messagebncount",messagebncount);
        return countmap;

    }

}
