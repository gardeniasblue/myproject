package com.blogmang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class LeaveMessage {

    private int id;
    @NotNull(message = "请输入昵称")
    private String leaveMessageator;
    @Email(message = "邮箱地址不合法")
    private String email;

    private String websiteUrl;
    @NotBlank(message = "请输入留言内容")
    private String leaveMessageBody;
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date leaveMessageCreateTime;
    private String replyBody;
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date replyTime;
    private int leaveMessageStatus;
}
