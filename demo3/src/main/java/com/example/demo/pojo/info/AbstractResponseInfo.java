package com.example.demo.pojo.info;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
public abstract class AbstractResponseInfo extends Info{
    protected boolean success;
    protected String reason;

    public AbstractResponseInfo () {
    }

    public AbstractResponseInfo(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }

}
