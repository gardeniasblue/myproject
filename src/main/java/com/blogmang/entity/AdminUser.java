package com.blogmang.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 后台管理员信息表
 * </p>
 *
 * @author: 南街
 * @since 2019-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdminUser implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 管理员id
     */

    private Integer adminUserId;

    /**
     * 管理员登陆名称
     */

    private String loginUserName;

    /**
     * 管理员登陆密码
     */

    private String loginPassword;

    /**
     * 管理员显示昵称
     */

    private String nickName;

    /**
     * 是否锁定 0未锁定 1已锁定无法登陆
     */

    private Integer locked;


    /**
     * 角色
     */
    private String role;



}
