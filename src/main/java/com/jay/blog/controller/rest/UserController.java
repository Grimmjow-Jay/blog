package com.jay.blog.controller.rest;

import com.jay.blog.entity.ResponseEntity;
import com.jay.blog.entity.User;
import com.jay.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/add")
    public ResponseEntity<Boolean> addUser(User user) {
        return ResponseEntity.success(userService.addUser(user));
    }
}
