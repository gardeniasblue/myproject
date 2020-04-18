package com.blogmang.config;

import com.blogmang.entity.AdminUser;
import com.blogmang.service.AdminUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Permissions;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    AdminUserService adminUserService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名


        Subject subject = SecurityUtils.getSubject();
        AdminUser user = (AdminUser) subject.getPrincipal();

        //根据用户名去数据库查询用户信息
       // AdminUser userRole = adminUserService.userLogin(user.getRole());
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

            //添加角色
            simpleAuthorizationInfo.addRole(user.getRole());
            //添加权限


        return simpleAuthorizationInfo;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        UsernamePasswordToken usertok = (UsernamePasswordToken) token;
        AdminUser user = adminUserService.userLogin(usertok.getUsername());
        if(user==null){
            return null;
        }else if(user.getLocked()==1){
            throw new LockedAccountException();
        }
        Subject sub = SecurityUtils.getSubject();
        Session session = sub.getSession();
        session.setAttribute("loginUser",user.getNickName());
        return new SimpleAuthenticationInfo(user,user.getLoginPassword(),"");
    }
}
