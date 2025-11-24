package com.example.service.Impl;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public User queryUserById(String userId) {
        return  userMapper.queryUserById(userId);
    }

    @Override
    public User queryUserByActiveCode(String activeCode) {
        return userMapper.queryUserByActiveCode(activeCode);
    }

    @Override
    public User loginByUserNameAndPasswordAndActiveStatus(User user) {
        return userMapper.loginByUserNameAndPasswordAndActiveStatus(user);
    }

    @Override
    public User AdminLogin(User user) {
        return userMapper.AdminLogin(user);
    }

    @Override
    public int addUser(User user) {
        Integer maxId = userMapper.getMaxId();
        if(maxId != null) {
            user.setUserId(String.valueOf(maxId+1));
        } else {
            user.setUserId("1");
        }
        return userMapper.addUser(user);
    }

    @Override
    public int deleteUser(String userId) {
        return userMapper.deleteUser(userId);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public List<User> queryAllUser(String keywords) {
        return userMapper.queryAllUser(keywords);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public User findByUserNameAndEmail(User user) {
        return userMapper.findByUserNameAndEmail(user);
    }
}
