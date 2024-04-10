package com.example.demo.pojo.info;

import com.example.demo.pojo.DO.UserDO;
import lombok.Data;

import java.util.Date;
@Data
public class UserInfo {
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
    private Date registrationTime;
    private Date lastLoginTime;
    private String profilePictureUrl;
    public UserInfo(UserDO userDO) {
        this.username = userDO.getUsername();
        this.name = userDO.getName();
        this.email = userDO.getEmail();
        this.phoneNumber = userDO.getPhoneNumber();
        this.registrationTime = userDO.getCreateTime();
        this.lastLoginTime = userDO.getLastLoginTime();
        this.profilePictureUrl = userDO.getProfilePictureUrl();
    }
}
