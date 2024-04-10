package com.example.demo.pojo.info;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString(callSuper = true)
public class LoginRequestInfo extends Info implements Serializable {
    private String username;
    private String password;
    private int infoType;

    public LoginRequestInfo() {}
    public LoginRequestInfo(String username, String password) {
        this.username = username;
        this.password = password;
        this.infoType = getInfoType();
    }

    @Override
    public int getInfoType() {
        return LoginRequestInfo;
    }
}
