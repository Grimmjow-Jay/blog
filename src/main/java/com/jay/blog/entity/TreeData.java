package com.jay.blog.entity;

import lombok.Data;

import java.util.List;

/**
 * 仅用于前端页面显示
 */
@Data
public class TreeData {
    private String title;
    private String id;
    private String field;
    private String href;
    private String parentId;
    private boolean spread = true;
    private boolean checked = false;
    private boolean disabled = false;
    private List<TreeData> children;
}
