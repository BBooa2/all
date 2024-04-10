package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.DO.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServerimpl implements UserServer {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean login(String username, String password) {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", password);
        userMapper.selectList(queryWrapper);
        UserDO find = userMapper.selectOne(queryWrapper);
        return find != null;
    }

    @Override
    public boolean register(String username, String nickname, String password, String confirmPassword, String email) {
//        System.out.println(1);
        // 检查参数是否为空
        if (username.equals("") || password.equals("") || confirmPassword.equals("") || nickname.equals("") || email.equals("")) {
            return false;
        }
//        System.out.println(2);
        // 检查密码和确认密码是否一致
        if (!password.equals(confirmPassword)) {
            return false;
        }
//        System.out.println(3);
        // 检查用户名是否已存在
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        UserDO existingUser = userMapper.selectOne(queryWrapper);
        if (existingUser != null) {
            return false;
        }
        // 创建新用户对象并插入数据库
        UserDO newUser = new UserDO();
        newUser.setUsername(username);
        // todo 密码加密
        newUser.setPassword(password);
        newUser.setNickname(nickname);
        newUser.setEmail(email);
        userMapper.insert(newUser);

        return true;
    }

    @Override
    public UserDO getUserInfo(String username) {
        return userMapper.selectByUsername(username);
    }
}
