package com.fir.org.common.exception;

/**
 * @author:黑绝
 * @date:2018/5/20 下午3:56
 */
public class CustomerException extends Exception{

    private static final long serialVersionUID = 1L;

    //定义无参构造方法
    public CustomerException(){
        super();
    }

    //定义有参数的构造方法
    public CustomerException(String msg){
        super(msg);
    }
}
