package com.blogmang.controller.admin;

import com.blogmang.dto.AjaxResultPage;
import com.blogmang.entity.BlogComment;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class GatewayController {
    /** 用户相关路由  /v1
     * 跳转至登录页面
     * @return
     */
    @RequestMapping("/login")
    public String toLogin(){
        return "admin/login";
    }

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("/v1/toindex")
    public String toIndex(){
        return "admin/index";
    }

    /**
     * 欢迎页
     * @return
     */
    @RequestMapping("/v1/welcome")
    public String toWelcome(){
        return "admin/welcome";
    }

    /**
     * 统计页
     * @return
     */
    @RequestMapping("/v1/welcome1")
    public String toWelcome1(){
        return "admin/welcome1";
    }



    /**
     * 跳转用户列表
     * @return
     */
    @RequestMapping("/v1/adminlist")
    public String toMemberlist(){

        return "admin/admin-list";
    }

    /**
     * 跳转添加用户页面
     * @return
     */
    @RequestMapping("/v1/adminadd")
    public String Memberadd(){

        return "admin/admin-add";
    }

    /**
     * 博客相关路由 /v2
     */

    /**
     * 跳转添加博客页面
     * @return
     */
    @RequestMapping("/v2/toaddblog")
    public String toAddBlog(){
        return "admin/blog-edit";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/v2/tobloglist")
    public String toBloglist(){
        return "admin/blog-list";
    }

    /**
     * 博客分类相关路由  /v3
     *
     */

    @RequestMapping("/v3/tocategory")
    public String toCategory(){
        return "admin/category-list";
    }

    @RequestMapping("/v3/tocategoryadd")
    public String toCategoryAdd(){
        return "admin/category-add";
    }
    /**
     * 标签相关路由  /v4
     */
    @RequestMapping("/v4/totaglist")
    public String toTagList(){
        return "admin/tag-list";
    }
    /**
     * 评论相关路由  /v5
     */
    @RequestMapping("/v5/tocommentlist")
    public String tocommentlist(){
        return "admin/comment-list";
    }

    /**
     * 更新系统相关
     * @return
     */
    @RequestMapping("/v8/tosystemupdate")
    public String tosystemupdate(){
        return "admin/systemupdate";
    }
}
