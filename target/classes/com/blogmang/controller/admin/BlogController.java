package com.blogmang.controller.admin;


import com.blogmang.constants.HttpStatusConstants;
import com.blogmang.constants.UploadConstants;
import com.blogmang.controller.vo.BlogDetailVO;
import com.blogmang.dto.AjaxResultPage;
import com.blogmang.dto.Result;
import com.blogmang.entity.*;
import com.blogmang.service.*;
import com.blogmang.util.DateUtils;
import com.blogmang.util.MyBlogUtils;
import com.blogmang.util.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class BlogController {
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
    private BlogUpdateService blogUpdateService;

    /**admin
     * 查询博客列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/v2/bloglist")
    public AjaxResultPage<BlogInfo> blogList(){

        List<BlogInfo> bloglist= blogService.queryAllBlog();
        AjaxResultPage<BlogInfo> resultPage = new AjaxResultPage<>();
        resultPage.setCode(0);
        resultPage.setCount(1000);
        resultPage.setData(bloglist);

        return resultPage;
    }
    /**admin
     * 添加博客，博客添加成功后将标签信息保存至标签关系库
     * @param blogTagIds
     * @param blogInfo
     * @return Result
     */
    @ResponseBody
    @PostMapping("/v2/blog/edit")
    public Result saveBlog(@RequestParam("blogTagIds[]") List<Integer> blogTagIds, BlogInfo blogInfo){

        Subject subject = SecurityUtils.getSubject();
        AdminUser user = (AdminUser) subject.getPrincipal();
        String username = user.getNickName();
        blogInfo.setCreateTime(DateUtils.getLocalCurrentDate());
        blogInfo.setUpdateTime(DateUtils.getLocalCurrentDate());
        if (blogInfo.getBlogStatus()==null){
            blogInfo.setBlogStatus(0);
        }
        blogInfo.setBlogAuthor(username);

        blogInfo.setIsDeleted(1);
        //截取标签，放入数组
        String blogtags=blogInfo.getBlogTags();
        List<String> tags = Arrays.asList(blogtags.split(","));
        BlogTagRelation blogTagRelation = new BlogTagRelation();
        boolean result = blogService.saveBlog(blogInfo);
        if (result){
            for (String tag : tags){
                //在标签库中根据标签名查标签id
                BlogTag blogTag = blogTagService.queryTagByTagName(tag);

                blogTagRelation.setBlogId(blogInfo.getBlogId());
                blogTagRelation.setTagId(blogTag.getTagId());
                blogTagRelation.setCreateTime(DateUtils.getLocalCurrentDate());
                //保存到标签库
                blogTagRelationService.addBlogTagRelation(blogTagRelation);
            }
            BlogUpdate blogUpdate = new BlogUpdate();
            blogUpdate.setBlogUpdateTitle(username+"更新了博客:  ");
            blogUpdate.setBlogUpdateCon(blogInfo.getBlogTitle());
            blogUpdate.setCreateTime(DateUtils.getLocalCurrentDate());
            blogUpdateService.addBlogUpdate(blogUpdate);
            return ResultGenerator.getResultByHttp(HttpStatusConstants.SUCCESS);
        }

        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

    /**
     * 删除博客
     * @param blogId
     * @return
     */
    @ResponseBody
    @RequestMapping("/v2/blogdelete")
    public Result blogDel(@RequestParam Long blogId){
        boolean result = blogService.blogDelete(blogId);
        blogTagRelationService.deleteByBlogId(blogId);
        if(result){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

    /**
     * 跳转修改博客页面（已存在）
     * @param blogId
     * @param model
     * @return
     */
    @RequestMapping("/v2/blogedit")
    public String blogEdit(@RequestParam("blogId") Long blogId,Model model){
          BlogInfo blog = blogService.queryBlogById(blogId);
          List<BlogTagRelation> tagList = blogTagRelationService.queryTagsByBlogId(blogId);
          List<Integer> taglist = new ArrayList<>();
          BlogTag blogTag = new BlogTag();
          for(BlogTagRelation blogTagRelation : tagList){
              blogTag = blogTagService.queryTagByTagId(blogTagRelation.getTagId());
              taglist.add(blogTag.getTagId());
          }
          model.addAttribute("blogTags",taglist);
          model.addAttribute("editblog",blog);
          return "admin/edit-blog";
    }

    /**
     * 博客内容图片上传
     * @param request
     * @param file
     * @return
     * @throws URISyntaxException
     */
    @ResponseBody
    @PostMapping("/v2/blog/uploadFile")
    public Map<String,Object> uploadFileByEditormd(HttpServletRequest request,
                                                   @RequestParam(name = "editormd-image-file", required = true)
                                                           MultipartFile file) throws URISyntaxException {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        Map<String,Object> result = new HashMap<>();
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = UploadConstants.FILE_UPLOAD_DIC; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            String fileUrl = MyBlogUtils.getHost(new URI(request.getRequestURL() + "")) +
                    UploadConstants.FILE_SHINE_UPON + fileName;
            file.transferTo(dest);
            result.put("success", 1);
            result.put("message","上传成功");
            result.put("url",fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/temp-rainy/" + fileName;

        return result;
    }

    /**
     * 保存修改博客的内容
     * @param blogTagIds
     * @param blogInfo
     * @return
     */
    @RequestMapping("/v2/blogeditsave")
    @ResponseBody
    public Result saveeditBlog(@RequestParam("blogTagIds[]") List<Integer> blogTagIds, BlogInfo blogInfo){

        Subject subject = SecurityUtils.getSubject();
        AdminUser user = (AdminUser) subject.getPrincipal();
        String username = user.getNickName();
        blogInfo.setUpdateTime(DateUtils.getLocalCurrentDate());
        if(blogInfo.getBlogStatus()==null){
            blogInfo.setBlogStatus(0);
        }
        blogInfo.setBlogAuthor(username);

        blogInfo.setIsDeleted(1);
        //截取标签，放入数组
        String blogtags=blogInfo.getBlogTags();
        List<String> tags = Arrays.asList(blogtags.split(","));
        BlogTagRelation blogTagRelation = new BlogTagRelation();


        if (blogService.updateBlog(blogInfo)){
            blogTagRelationService.deleteByBlogId(blogInfo.getBlogId());
            for (String tag : tags){
                //在标签库中根据标签名查标签id
                BlogTag blogTag = blogTagService.queryTagByTagName(tag);

                blogTagRelation.setBlogId(blogInfo.getBlogId());
                blogTagRelation.setTagId(blogTag.getTagId());
                blogTagRelation.setCreateTime(DateUtils.getLocalCurrentDate());
                //保存到标签库
                //blogTagRelationService.updateTagRelation(blogInfo.getBlogId(),blogTagRelation);

                blogTagRelationService.addBlogTagRelation(blogTagRelation);
            }
            return ResultGenerator.getResultByHttp(HttpStatusConstants.SUCCESS);
        }

        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

    /**
     * 修改博客状态 0：草稿 ， 1：发布
     * @param blogInfo
     * @return
     */
    @RequestMapping("/v2/blogstatus")
    @ResponseBody
    public Result saveeditBlogstatus(BlogInfo blogInfo){
        blogInfo.setUpdateTime(DateUtils.getLocalCurrentDate());
        boolean result = blogService.updateBlogStatus(blogInfo);
        if(result){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.SUCCESS);
        }

        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

    /**
     * 修改博客是否可评论 0：允许 ， 1：不允许
     * @param blogInfo
     * @return
     */
    @RequestMapping("/v2/blogenablecomment")
    @ResponseBody
    public Result saveeditBlogcomment(BlogInfo blogInfo){
        blogInfo.setUpdateTime(DateUtils.getLocalCurrentDate());
        boolean result = blogService.updateBlogenableComment(blogInfo);
        if(result){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.SUCCESS);
        }

        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

    /**
     * admin搜索博客
     * @param blogInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/v2/adminsearchblog")
    public AjaxResultPage<BlogInfo> searchBlogAdmin(BlogInfo blogInfo){
        if(blogInfo.getBlogCategoryId() == null){
            List<BlogInfo> blogInfos = blogService.selectBlogByBlogTitle(blogInfo.getBlogTitle());

            AjaxResultPage<BlogInfo> resultPage = new AjaxResultPage<>();
            resultPage.setCode(0);
            resultPage.setCount(1000);
            resultPage.setData(blogInfos);

            return resultPage;
        }
        List<BlogInfo> blogInfoList = blogService.searchBlog(blogInfo);
        AjaxResultPage<BlogInfo> resultPage = new AjaxResultPage<>();
        resultPage.setCode(0);
        resultPage.setCount(1000);
        resultPage.setData(blogInfoList);

        return resultPage;
    }

    @ResponseBody
    @GetMapping("v1/blog/select")
    public List<BlogInfo> getBlogInfoSelect(){
        return blogService.queryAllBlog();
    }
}
