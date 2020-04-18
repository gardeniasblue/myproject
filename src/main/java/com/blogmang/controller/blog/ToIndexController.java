package com.blogmang.controller.blog;

import com.blogmang.entity.BlogInfo;
import com.blogmang.entity.BlogTag;
import com.blogmang.service.BlogService;
import com.blogmang.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ToIndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogTagService blogTagService;

    @GetMapping({"/", "/index", "index.html"})
    public String index(Model model) {

        List<BlogInfo> blogInfoList = blogService.queryAllBlogView();
        List<BlogTag> blogTagList = blogTagService.queryAllTags();
        List<BlogInfo> newsBlog = blogService.selectBlogByNew();
        List<BlogInfo> blogViews = blogService.selectBlogByClick();

        model.addAttribute("bloglist",blogInfoList);
        model.addAttribute("blogtaglist",blogTagList);
        model.addAttribute("newsblog",newsBlog);
        model.addAttribute("blogviews",blogViews);

        return "blog/amaze/index";
    }

}