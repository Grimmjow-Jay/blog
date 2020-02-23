package com.jay.blog.controller;

import com.jay.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ArticleController {

    private final BlogService blogService;

    @Autowired
    public ArticleController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping("/article")
    public String article(String articleId, HttpServletRequest request) {
        request.setAttribute("article", blogService.getArticleById(articleId));
        return "article";
    }
}
