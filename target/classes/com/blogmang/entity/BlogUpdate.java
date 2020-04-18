package com.blogmang.entity;

import lombok.Data;

import java.util.Date;
@Data
public class BlogUpdate {

    private int id;
    private String blogUpdateTitle;
    private String blogUpdateCon;
    private String author;
    private Date createTime;
}
