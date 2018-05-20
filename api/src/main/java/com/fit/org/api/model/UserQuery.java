package com.fit.org.api.model;

/**
 * @author:黑绝
 * @date:2018/5/19 下午10:00
 */
public class UserQuery extends BaseQuery{

    private String code;

    private String openId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
