package BoaHancock.pojo.info;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class RegisterResponseInfo extends AbstractResponseInfo{
    private String from;
    private String message;
    private int infoType;

    public RegisterResponseInfo(boolean success, String reason) {
        super(success, reason);
    }
    public RegisterResponseInfo(String from, String message) {
        this.from = from;
        this.message = message;
        this.infoType = getInfoType();
    }

    public RegisterResponseInfo() {
    }

    @Override
    public int getInfoType() {
        return LoginResponseInfo;
    }
}
