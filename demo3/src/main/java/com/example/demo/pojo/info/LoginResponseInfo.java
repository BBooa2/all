package com.example.demo.pojo.info;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class LoginResponseInfo extends Info {
    private boolean success;
    private String message;
    private UserInfo userInfo;
    private int infoType;

//    public LoginResponseInfo(boolean success, String reason) {
//        super(success, reason);
//        this.success = success;
//        this.infoType = getInfoType();
//    }
    public LoginResponseInfo(boolean success, String message, UserInfo userInfo) {
        this.success = success;
        this.message = message;
        this.userInfo = userInfo;
        this.infoType = getInfoType();
    }
    public LoginResponseInfo(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.infoType = getInfoType();
    }
    public LoginResponseInfo() {
    }

    @Override
    public int getInfoType() {
        return LoginResponseInfo;
    }
}
