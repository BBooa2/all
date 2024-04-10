package BoaHancock.pojo.info;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
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
    public LoginResponseInfo(boolean success, String message,UserInfo userInfo) {
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
//    public UserInfo getUserInfo() {
//        return userInfo;
//    }

    @Override
    public int getInfoType() {
        return LoginResponseInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void setInfoType(int infoType) {
        this.infoType = infoType;
    }
}
