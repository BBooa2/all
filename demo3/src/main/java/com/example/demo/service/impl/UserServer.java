package com.example.demo.service.impl;

import com.example.demo.pojo.DO.UserDO;

public interface UserServer {
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 登陆成功返回 true, 否则返回 false
     */
    boolean login(String username, String password);

    /**
     * 用户注册
     * @param username 用户名
     * @param nickname 昵称
     * @param password 密码
     * @param confirmPassword 确认密码
     * @param email 邮箱
     * @return 注册成功就是true 否则就是 false
     */
    boolean register(String username, String nickname, String password, String confirmPassword, String email);

    public UserDO getUserInfo(String username);
}
