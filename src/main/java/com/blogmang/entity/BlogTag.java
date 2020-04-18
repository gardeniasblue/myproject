package com.blogmang.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 标签表
 * </p>
 *
 * @author: 南街
 * @since 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogTag implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 标签表主键id
     */

    private Integer tagId;

    /**
     * 标签名称
     */

    private String tagName;

    /**
     * 是否删除 0=否 1=是
     */

    private Integer isDeleted;

    /**
     * 创建时间
     */

    private Date createTime;


}
