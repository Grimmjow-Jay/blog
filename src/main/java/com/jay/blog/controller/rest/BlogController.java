package com.jay.blog.controller.rest;

import com.jay.blog.entity.Article;
import com.jay.blog.entity.Discussion;
import com.jay.blog.entity.ResponseEntity;
import com.jay.blog.entity.TreeData;
import com.jay.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/articleCount")
    public ResponseEntity<Integer> articleCount() {
        return ResponseEntity.success(blogService.articleCount());
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> articles(int page, int size) {
        return ResponseEntity.success(blogService.articles(page, size));
    }

    @RequestMapping("/addArticle")
    public ResponseEntity<Boolean> addArticle(Article article) {
        return ResponseEntity.success(blogService.addArticle(article));
    }

    @RequestMapping("/addDiscussion")
    public ResponseEntity<Boolean> addDiscussion(Discussion discussion) {
        return ResponseEntity.success(blogService.addDiscussion(discussion));
    }

    @RequestMapping("/getDiscussion")
    public ResponseEntity<List<Discussion>> getDiscussion(String articleId) {
        return ResponseEntity.success(blogService.getDiscussion(articleId));
    }

    @RequestMapping("/getDiscussionTreeData")
    public ResponseEntity<List<TreeData>> getDiscussionTreeData(String articleId) {
        return ResponseEntity.success(blogService.getDiscussionTreeData(articleId));
    }

}
