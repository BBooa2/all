package com.example.demo.pojo.info;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
public class RegisterRequestInfo extends Info implements Serializable {

    private String username;
    private String password;
    private String confirmPassword;
    private String nickname;
    private String email;
    private int infoType;

    public RegisterRequestInfo(){}
    public RegisterRequestInfo(String username, String password, String confirmPassword, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.nickname = nickname;
        this.email = email;
        this.infoType = getInfoType();
    }

    @Override
    public int getInfoType() {
        return RegisterRequestInfo;
    }

}
