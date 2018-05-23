package com.fit.org.api.model.dto;

import java.util.Date;

/**
 * @author:黑绝
 * @date:2018/5/20 下午9:55
 */
public class RequirementDTO {

    private String userCode;

    private Integer requirementType;

    private Integer difficulty;

    private Date gmtModified;

    private Date gmtCreate;

    private String requirementTypeName;

    private Float kgNum;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getRequirementType() {
        return requirementType;
    }

    public void setRequirementType(Integer requirementType) {
        this.requirementType = requirementType;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Float getKgNum() {
        return kgNum;
    }

    public void setKgNum(Float kgNum) {
        this.kgNum = kgNum;
    }

    public String getRequirementTypeName() {
        return requirementTypeName;
    }

    public void setRequirementTypeName(String requirementTypeName) {
        this.requirementTypeName = requirementTypeName;
    }
}
