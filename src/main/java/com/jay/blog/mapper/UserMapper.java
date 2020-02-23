package com.jay.blog.mapper;

import com.jay.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface UserMapper {

    int addUser(User user);

    List<Long> userIds();
}
