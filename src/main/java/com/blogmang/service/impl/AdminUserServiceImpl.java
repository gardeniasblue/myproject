package com.blogmang.service.impl;

import com.blogmang.dao.AdminUserDao;
import com.blogmang.entity.AdminUser;
import com.blogmang.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    public AdminUser userLogin(String loginUserName) {

        return adminUserDao.userLogin(loginUserName);
    }

    public boolean addUser(AdminUser adminUser) {
        adminUserDao.addUser(adminUser);
        return true;
    }

    public List<AdminUser> userList() {
        return adminUserDao.userList();
    }

    public boolean deleteUser(Long id) {
        adminUserDao.deleteUser(id);
        return true;
    }

    @Override
    public AdminUser queryUserById(Integer adminUserId) {
        return adminUserDao.queryUserById(adminUserId);
    }

    @Override
    public boolean updateUserById(AdminUser adminUser) {
        adminUserDao.updateUserById(adminUser);
        return true;
    }

    @Override
    public AdminUser examineUser(String loginUserName) {
        return adminUserDao.examineUser(loginUserName);
    }

    @Override
    public boolean disableUser(Integer adminUserId, Integer locked) {
        adminUserDao.disableUser(adminUserId,locked);
        return true;
    }

    @Override
    public List<AdminUser> searchUser(String loginUserName, String nickName) {
        return adminUserDao.searchUser(loginUserName,nickName);
    }

    @Override
    public Integer countUser() {
        return adminUserDao.countUser();
    }
}
