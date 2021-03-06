package com.fit.org.api.model;

/**
 * @author:黑绝
 * @date:2018/4/29 22:10
 */
public class Result<T> {

    private Boolean isSuccess;

    private T data;

    private String errorMsg;

    public static <T> Result <T> success(T t){
        Result result = new Result();
        result.setSuccess(true);
        result.setData(t);
        return result;
    }

    public static <T> Result<T> error(String errorMsg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setErrorMsg(errorMsg);
        return result;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
