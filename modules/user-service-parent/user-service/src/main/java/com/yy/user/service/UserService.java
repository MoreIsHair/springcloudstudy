package com.yy.user.service;

import com.yy.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YY
 * @date 2020/1/6
 * @description
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
}
