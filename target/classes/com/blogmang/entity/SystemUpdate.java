package com.blogmang.entity;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class SystemUpdate {

    private int id;
    @NotBlank(message = "请输入更新的标题")
    private String systemUpdateTitle;
    @NotBlank(message = "请输入更新内容")
    private String systemUpdateCon;
    @NotBlank(message = "请输入版本号")
    private String systemUpdateEdition;
    private Date createTime;


}
