package com.jay.blog.service.impl;

import com.jay.blog.entity.User;
import com.jay.blog.mapper.UserMapper;
import com.jay.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.addUser(user) == 1;
    }

    @Override
    public List<Long> userIds() {
        return userMapper.userIds();
    }
}
