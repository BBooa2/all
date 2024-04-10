package com.example.demo.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_user")
public class UserDO {
    private long id;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String name;
    private String phoneNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;
    private Date modifiedTime;
    private Date lastLoginTime;
    private String profilePictureUrl;
}
