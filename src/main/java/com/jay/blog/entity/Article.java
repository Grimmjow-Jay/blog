package com.jay.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * 文章
 */
@Data
public class Article {
    private String articleId;
    private String title;
    private String description;
    private String content;
    private User user;
    private Integer discussionCount;
    private Date createTime;
    private Date updateTime;
}
