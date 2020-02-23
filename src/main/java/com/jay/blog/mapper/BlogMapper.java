package com.jay.blog.mapper;

import com.jay.blog.entity.Article;
import com.jay.blog.entity.Discussion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface BlogMapper {

    int articleCount();

    List<Article> articles(@Param("start") int start, @Param("size") int size);

    List<String> articleIds();

    int addArticle(Article article);

    int addDiscussion(Discussion discussion);

    Article getArticleById(String articleId);

    List<Discussion> getDiscussionByArticleId(String articleId);
}
