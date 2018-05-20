package com.fit.org.api.model;

import java.util.Date;

/**
 * @author:黑绝
 * @date:2018/5/20 下午3:35
 */
public class UserDTO {

    /**
     * 微信昵称
     */
    private String name;

    /**
     * 来自国家
     */
    private String country;

    /**
     * 来及省份
     */
    private String province;

    /**
     * 微信openId，唯一
     */
    private String openId;

    /**
     * code
     */
    private String code;

    /**
     * 记录生成时间
     */
    private Date gmtCreate;

    /**
     * 记录修改时间
     */
    private Date gmtModified;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 体重，单位千克
     */
    private Integer weight;

    /**
     * 身高，单位厘米
     */
    private Integer height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public String getOpenId() {
        return openId;
    }

    public String getProvince() {
        return province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
