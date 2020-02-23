package com.jay.blog.service;

import com.jay.blog.entity.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);

    List<Long> userIds();
}
