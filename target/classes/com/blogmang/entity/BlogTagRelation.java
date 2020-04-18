package com.blogmang.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 博客跟标签的关系表
 * </p>
 *
 * @author: 南街
 * @since 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogTagRelation implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 关系表id
     */
    private Long relationId;

    /**
     * 博客id
     */
    private Long blogId;

    /**
     * 标签id
     */
    private Integer tagId;

    /**
     * 添加时间
     */
    private Date createTime;


}
