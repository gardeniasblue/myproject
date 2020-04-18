package com.blogmang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 评论信息表
 * </p>
 *
 * @author: 南街
 * @since 2019-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogComment implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 主键id
     */

    private Long commentId;

    /**
     * 关联的blog主键
     */

    @NotNull(message = "非法请求")
    @Min(value = 0,message = "非法请求")
    private Long blogId;

    /**
     * 评论者名称
     */

    @NotBlank(message = "请输入称呼")
    @Length(min = 1,max = 6,message = "名称过长或过短")
    private String commentator;

    /**
     * 评论人的邮箱
     */

    @Email(message = "邮箱地址不合法")
    private String email;

    /**
     * 网址
     */

    private String websiteUrl;

    /**
     * 评论内容
     */

    @NotBlank(message = "请输入评论内容")
    @Length(min = 1,max = 200,message = "评论内容过长或过短")
    private String commentBody;

    /**
     * 评论提交时间
     */

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")    //将Date转换成String,一般后台传值给前台时
    private Date commentCreateTime;

    /**
     * 评论时的ip地址
     */

    private String commentatorIp;

    /**
     * 回复内容
     */

    private String replyBody;

    /**
     * 回复时间
     */

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date replyCreateTime;

    /**
     * 是否审核通过 0-未审核 1-审核通过
     */
    private Integer commentStatus;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Integer isDeleted;


}
