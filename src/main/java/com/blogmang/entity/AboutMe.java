package com.blogmang.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AboutMe {
    private int id;
    private String title;
    private String aboutcontext;
    private Integer enablecomment;
    private Date createtime;
}
