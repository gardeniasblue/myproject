package com.blogmang.controller.admin;

import com.blogmang.constants.HttpStatusConstants;
import com.blogmang.dto.AjaxResultPage;
import com.blogmang.dto.Result;
import com.blogmang.entity.AboutMe;
import com.blogmang.entity.AdminUser;
import com.blogmang.service.AboutMeService;
import com.blogmang.service.AdminUserService;
import com.blogmang.util.DateUtils;
import com.blogmang.util.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AboutMeService aboutMeService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {


        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.UNKNOW_USER, "/admin/login");
        } catch (IncorrectCredentialsException ice) {
            //密码不正确
            return ResultGenerator.getResultByHttp(HttpStatusConstants.PASSWORD_ERROR, "/admin/login");
        } catch (LockedAccountException lae) {
            //账户锁定
            return ResultGenerator.getResultByHttp(HttpStatusConstants.STATIC_ERROR, "/admin/login");
        } catch (ExcessiveAttemptsException eae) {
            //用户名或密码错误次数过多
            return ResultGenerator.getResultByHttp(HttpStatusConstants.PASSWORDNUMBER_OUT, "/admin/login");
        } catch (AuthenticationException ae) {
            //用户名或密码不正确
            return ResultGenerator.getResultByHttp(HttpStatusConstants.USERORPASSWORD_ERROR,"/admin/login");
        }
        if (subject.isAuthenticated()) {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK, "/admin/v1/toindex");
        } else {
            token.clear();
            return ResultGenerator.getResultByHttp(HttpStatusConstants.USERORPASSWORD_ERROR, "/admin/login");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/v1/toadminadd",method = RequestMethod.POST)
    public Result<String> toMemberadd(
            @RequestParam("username") String username,
            @RequestParam("nickName") String nickName,
            @RequestParam("password") String password,
            @RequestParam("role") String role){


        AdminUser adminUser1 = adminUserService.examineUser(username);
        if(adminUser1 != null){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.USERBEEXIST);
        }
        AdminUser adminUser = new AdminUser();
        adminUser.setLoginUserName(username);
        adminUser.setLoginPassword(password);
        adminUser.setRole(role);
        adminUser.setNickName(nickName);

        adminUserService.addUser(adminUser);

        return ResultGenerator.getResultByHttp(HttpStatusConstants.SUCCESS);
    }
    @ResponseBody
    @RequestMapping("/v1/userlist")
    public AjaxResultPage<AdminUser> usersList(){

        List<AdminUser> userlist= adminUserService.userList();
        AjaxResultPage<AdminUser> resultPage = new AjaxResultPage<>();
        resultPage.setCode(0);
        resultPage.setCount(1000);
        resultPage.setData(userlist);

        return resultPage;
    }
    @ResponseBody
    @PostMapping("/v1/deleteuser")
    public Result deleteBlog(@RequestParam Long id){

        boolean flag = adminUserService.deleteUser(id);
        if (flag){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.SUCCESS);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }
    @RequestMapping("/v1/edituser/{adminUserId}")
    public String edituser(@PathVariable("adminUserId") Integer adminUserId, Model model){

        AdminUser adminUser = adminUserService.queryUserById(adminUserId);

        model.addAttribute("adminuser",adminUser);

        return "admin/admin-edit";
    }
    @ResponseBody
    @RequestMapping(value = "/v1/toadminedit",method = RequestMethod.POST)
    public Result<String> toadminedit(
            @RequestParam("adminUserId") Integer adminUserId,
            @RequestParam("loginUserName") String loginUserName,
            @RequestParam("nickName") String nickName,
            @RequestParam("locked") Integer locked,
            @RequestParam("role") String role,
            @RequestParam("loginPassword") String loginPassword) {

        AdminUser adminUser = new AdminUser();
        AdminUser adminUser1 = adminUserService.queryUserById(adminUserId);


        if (loginUserName.equals(adminUser1.getLoginUserName())) {
            adminUser.setAdminUserId(adminUserId);
            adminUser.setLoginUserName(loginUserName);
            adminUser.setLoginPassword(loginPassword);
            adminUser.setNickName(nickName);
            adminUser.setLocked(locked);
            adminUser.setRole(role);
            Boolean aBoolean = adminUserService.updateUserById(adminUser);
            if(aBoolean){
                return ResultGenerator.getResultByHttp(HttpStatusConstants.SUCCESS);
            }else {
                return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
            }
        }else {
            AdminUser adminUser2 = adminUserService.examineUser(loginUserName);
            if (adminUser2 == null){
                adminUser.setAdminUserId(adminUserId);
                adminUser.setLoginUserName(loginUserName);
                adminUser.setLoginPassword(loginPassword);
                adminUser.setNickName(nickName);
                adminUser.setLocked(locked);
                adminUser.setRole(role);
                Boolean aBoolean = adminUserService.updateUserById(adminUser);
                if(aBoolean){
                    return ResultGenerator.getResultByHttp(HttpStatusConstants.SUCCESS);
                }else {
                    return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
                }
            } else {
                return ResultGenerator.getResultByHttp(HttpStatusConstants.USERBEEXIST);
            }
        }

    }

    @ResponseBody
    @RequestMapping("/v1/enableUser")
    public Result<String> enableUser(@RequestParam("adminUserId") Integer adminUserId,
                                         @RequestParam("locked") Integer locked){
        Boolean bool = adminUserService.disableUser(adminUserId,locked);

        if(bool){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.SUCCESS);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);

    }

    @ResponseBody
    @RequestMapping("/v1/searchuser")
    public AjaxResultPage<AdminUser> searchUser(@RequestParam("loginUserName") String loginUserName,
                                                @RequestParam("nickName") String nickName){

        List<AdminUser> userlist= adminUserService.searchUser(loginUserName,nickName);
        AjaxResultPage<AdminUser> resultPage = new AjaxResultPage<>();
        resultPage.setCode(0);
        resultPage.setCount(1000);
        resultPage.setData(userlist);
        return resultPage;
    }
    @RequestMapping("/v1/toaboutme")
    public String tosave(Model model){
        AboutMe aboutMe = aboutMeService.queryAboutMe();
        model.addAttribute("about",aboutMe);
        return "admin/editaboutme";
    }
    /**
     * 登出
     * @return
     */
    @RequestMapping("/v1/logout")
    public String toLogout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "admin/login";
    }
    @ResponseBody
    @RequestMapping("/v1/saveaboutme")
    public Result save(AboutMe aboutMe){
        AboutMe aboutMeSql = aboutMeService.queryAboutMe();
        aboutMe.setCreatetime(DateUtils.getLocalCurrentDate());
        if(aboutMeSql !=null){
            aboutMe.setId(aboutMeSql.getId());
            aboutMeService.updateAboutMe(aboutMe);

        }else{
            aboutMeService.saveAboutMe(aboutMe);

        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.SUCCESS);
    }

}
