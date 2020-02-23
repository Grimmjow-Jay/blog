package com.jay.blog.service.impl;

import com.jay.blog.entity.Article;
import com.jay.blog.entity.Discussion;
import com.jay.blog.entity.TreeData;
import com.jay.blog.mapper.BlogMapper;
import com.jay.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogMapper blogMapper;

    @Autowired
    public BlogServiceImpl(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Override
    public int articleCount() {
        return blogMapper.articleCount();
    }

    @Override
    public List<Article> articles(int page, int size) {
        return blogMapper.articles(size * (page - 1), size);
    }

    @Override
    public List<String> articleIds() {
        return blogMapper.articleIds();
    }

    @Override
    public boolean addArticle(Article article) {
        return blogMapper.addArticle(article) == 1;
    }

    @Override
    public boolean addDiscussion(Discussion discussion) {
        return blogMapper.addDiscussion(discussion) == 1;
    }

    @Override
    public Article getArticleById(String articleId) {
        return blogMapper.getArticleById(articleId);
    }

    @Override
    public List<Discussion> getDiscussion(String articleId) {
        List<Discussion> discussionList = blogMapper.getDiscussionByArticleId(articleId);
        Map<String, Discussion> discussionMap = new HashMap<>();
        discussionList.forEach(e -> discussionMap.put(e.getDiscussionId(), e));
        Iterator<Discussion> iterator = discussionList.iterator();
        while (iterator.hasNext()) {
            Discussion discussion = iterator.next();
            String parentId = discussion.getParentId();
            if (parentId != null) {
                Discussion parentDiscussion = discussionMap.get(parentId);
                List<Discussion> children = parentDiscussion.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    parentDiscussion.setChildren(children);
                }
                children.add(discussion);
                iterator.remove();
            }
        }

        return discussionList;
    }

    @Override
    public List<TreeData> getDiscussionTreeData(String articleId) {
        List<Discussion> discussionList = blogMapper.getDiscussionByArticleId(articleId);
        List<TreeData> treeDataList = new ArrayList<>();
        Map<String, TreeData> treeDataMap = new HashMap<>();
        for (Discussion discussion : discussionList) {
            TreeData treeData = new TreeData();
            // title: “用户名： content”
            treeData.setTitle(discussion.getUser().getUserName() + ": " + discussion.getContent());
            treeData.setId(discussion.getDiscussionId());
            treeData.setParentId(discussion.getParentId());
            treeDataList.add(treeData);
            treeDataMap.put(treeData.getId(), treeData);
        }
        Iterator<TreeData> iterator = treeDataList.iterator();
        while (iterator.hasNext()) {
            TreeData treeData = iterator.next();
            String parentId = treeData.getParentId();
            if (parentId != null) {
                TreeData parentTreeData = treeDataMap.get(parentId);
                List<TreeData> children = parentTreeData.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    parentTreeData.setChildren(children);
                }
                children.add(treeData);
                iterator.remove();
            }
        }

        return treeDataList;
    }

}
