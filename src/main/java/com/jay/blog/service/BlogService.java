package com.jay.blog.service;

import com.jay.blog.entity.Article;
import com.jay.blog.entity.Discussion;
import com.jay.blog.entity.TreeData;

import java.util.List;

public interface BlogService {

    /**
     * 文章总数
     */
    int articleCount();

    /**
     * 文章列表
     *
     * @param page 页码
     * @param size 每页数量
     */
    List<Article> articles(int page, int size);

    /**
     * 获取article的id列表
     */
    List<String> articleIds();

    /**
     * 新增文章
     *
     * @param article 文章
     */
    boolean addArticle(Article article);

    /**
     * 新增评论
     *
     * @param discussion 评论
     */
    boolean addDiscussion(Discussion discussion);

    Article getArticleById(String articleId);

    List<Discussion> getDiscussion(String articleId);

    List<TreeData> getDiscussionTreeData(String articleId);
}
