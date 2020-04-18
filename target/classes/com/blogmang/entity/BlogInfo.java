package com.blogmang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 博客信息表
 * </p>
 *
 * @author: 南街
 * @since 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogInfo implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 博客表主键id
     */

    private Long blogId;

    /**
     * 博客标题
     */
    private String blogTitle;

    /**
     * 博客自定义路径url
     */
    private String blogSubUrl;

    /**
     * 博客前言
     */
    private String blogPreface;

    /**
     * 博客内容
     */
    private String blogContent;


    private String blogAuthor;

    /**
     * 博客分类id
     */
    private Integer blogCategoryId;

    /**
     * 博客分类(冗余字段)
     */
    private String blogCategoryName;

    /**
     * 博客标签(冗余字段)
     */
    private String blogTags;

    /**
     * 0-草稿 1-发布
     */
    private Integer blogStatus;

    /**
     * 阅读量
     */
    private Long blogViews;

    /**
     * 0-允许评论 1-不允许评论
     */
    private Integer enableComment;

    /**
     * 是否删除 0=否 1=是
     */

    private Integer isDeleted;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")    //将Date转换成String,一般后台传值给前台时
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")    //将Date转换成String,一般后台传值给前台时
    private Date updateTime;


}
