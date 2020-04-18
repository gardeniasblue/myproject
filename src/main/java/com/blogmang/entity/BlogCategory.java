package com.blogmang.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 博客分类
 * </p>
 *
 * @author: 南街
 * @since 2019-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogCategory implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 分类表主键
     */

    private Integer categoryId;

    /**
     * 分类的名称
     */
    private String categoryName;

    /**
     * 分类的图标
     */

    private String categoryIcon;

    /**
     * 分类的排序值 被使用的越多数值越大
     */

    private Integer categoryRank;

    /**
     * 是否删除 0=否 1=是
     */

    private Integer isDeleted;

    /**
     * 创建时间
     */

    private Date createTime;


}
