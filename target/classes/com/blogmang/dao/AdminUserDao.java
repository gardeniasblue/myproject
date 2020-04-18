package com.blogmang.dao;

import com.blogmang.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminUserDao {

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
