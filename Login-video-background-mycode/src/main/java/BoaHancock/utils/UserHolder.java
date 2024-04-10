package BoaHancock.utils;

import BoaHancock.pojo.info.UserInfo;

public class UserHolder {
    private final static ThreadLocal<UserInfo> userNow = new ThreadLocal<>();
    public static void set(UserInfo userInfo) {
        userNow.set(userInfo);
    }
    public static UserInfo get() {
        return userNow.get();
    }
    public static void remove() {
        userNow.remove();
    }
}
