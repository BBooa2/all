package BoaHancock.pojo.info;

import lombok.Data;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Info implements Serializable {
    public static Class<?> getInfoClass(int infoType) {
        return infoClass.get(infoType);
    }
    public abstract int getInfoType();
    public static final int LoginRequestInfo = 0;
    public static final int LoginResponseInfo = 1;
    public static final int RegisterRequestInfo = 2;
    public static final int RegisterResponseInfo = 3;
    private static final Map<Integer, Class<?>> infoClass = new HashMap<>();
    static {
        infoClass.put(LoginRequestInfo, LoginRequestInfo.class);
        infoClass.put(LoginResponseInfo, LoginResponseInfo.class);
        infoClass.put(RegisterRequestInfo, RegisterRequestInfo.class);
        infoClass.put(RegisterResponseInfo, RegisterResponseInfo.class);
    }
}
