package com.jay.blog.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 讨论
 */
@Data
public class Discussion {
    private String discussionId;
    private String content;
    private Date createTime;
    private Date updateTime;
    private String parentId;
    private Article article;
    private User user;
    private List<Discussion> children;
}
