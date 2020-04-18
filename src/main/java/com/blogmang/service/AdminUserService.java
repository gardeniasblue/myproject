package com.blogmang.service;

import com.blogmang.entity.AdminUser;

import java.util.List;


public interface AdminUserService {
    AdminUser userLogin(String loginUserName);
    boolean addUser(AdminUser adminUser);
    List<AdminUser> userList();
    boolean deleteUser(Long id);
    AdminUser queryUserById(Integer adminUserId);
    boolean updateUserById(AdminUser adminUser);
    AdminUser examineUser(String loginUserName);
    boolean disableUser(Integer adminUserId, Integer locked);
    List<AdminUser> searchUser(String loginUserName, String nickName);
    Integer countUser();
}
