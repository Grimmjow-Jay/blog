package com.jay.blog.controller.rest;

import com.jay.blog.entity.ResponseEntity;
import com.jay.blog.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    /**
     * 造数据
     */
    @RequestMapping("/createData")
    public ResponseEntity<Boolean> createData() {
        testService.addUser();
        testService.addArticle();
        testService.addDiscussion();
        return ResponseEntity.success(true);
    }

}
