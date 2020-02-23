package com.jay.blog.test;

import com.jay.blog.entity.Article;
import com.jay.blog.entity.Discussion;
import com.jay.blog.entity.User;
import com.jay.blog.service.BlogService;
import com.jay.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestService {

    private final Random random = new Random();

    private final UserService userService;
    private final BlogService blogService;

    @Autowired
    public TestService(UserService userService, BlogService blogService) {
        this.userService = userService;
        this.blogService = blogService;
    }

    /**
     * 增加10000个用户
     */
    public void addUser() {
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setUserName("UserName_" + UUID.randomUUID().toString().substring(0, 8));
            int randomValue = random.nextInt();
            user.setGender(randomValue % 2 == 0 ? "男" : "女");
            user.setPhone(10000000000L + randomValue + "");
            userService.addUser(user);
        }
    }

    /**
     * 增加10000篇文章
     */
    public void addArticle() {
        for (int i = 0; i < 10000; i++) {
            Article article = new Article();
            article.setArticleId(UUID.randomUUID().toString());
            StringBuilder content = new StringBuilder("Content-" + article.getArticleId() + "--");
            for (int j = 0; j < random.nextInt(50); j++) {
                content.append(UUID.randomUUID());
            }
            article.setContent(content.toString());
            article.setCreateTime(new Date());
            article.setDescription("Description-" + article.getArticleId() + "--" + UUID.randomUUID().toString());
            article.setTitle("Title-" + article.getArticleId());
            article.setUpdateTime(new Date());
            User user = new User();
            user.setUserId((long) random.nextInt(i + 1));
            article.setUser(user);
            blogService.addArticle(article);
        }
    }

    /**
     * 增加评论
     */
    public void addDiscussion() {
        List<String> articleIds = blogService.articleIds();
        List<Long> userIds = userService.userIds().subList(0, 500);
        int randomInt = 200;
        for (int i = 0; i < 500; i++) {
            String articleId = articleIds.get(random.nextInt(articleIds.size()));
            Article article = new Article();
            article.setArticleId(articleId);
            List<String> parentDiscussionIds = new ArrayList<>();
            parentDiscussionIds.add(null);
            randomInt = randomInt < 20 ? randomInt : randomInt - 1;
            int randomCount = random.nextInt(randomInt);
            for (int j = 0; j < randomCount; j++) {
                Discussion discussion = new Discussion();
                String discussionId = UUID.randomUUID().toString();
                discussion.setDiscussionId(discussionId);
                discussion.setParentId(parentDiscussionIds.get(random.nextInt(parentDiscussionIds.size())));
                parentDiscussionIds.add(discussionId);
                discussion.setArticle(article);
                User user = new User();
                user.setUserId(userIds.get(random.nextInt(userIds.size())));
                if (userIds.size() > 50) {
                    userIds.remove(userIds.size() - 1);
                }
                discussion.setUser(user);
                discussion.setContent("DiscussionContent_" + UUID.randomUUID().toString());
                discussion.setCreateTime(new Date());
                discussion.setUpdateTime(new Date());
                blogService.addDiscussion(discussion);
            }
        }
    }
}
