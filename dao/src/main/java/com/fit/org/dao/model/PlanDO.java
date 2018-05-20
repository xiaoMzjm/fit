package com.fit.org.dao.model;

/**
 * @author:黑绝
 * @date:2018/5/19 下午3:37
 */
public class PlanDO {

    /**
     * 用户code
     */
    private String userCode;

    /**
     * 教程code
     */
    private String courseCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
