package com.fir.org.common.util;

import java.util.UUID;

/**
 * @author:黑绝
 * @date:2018/5/19 下午3:06
 */
public class UUIDUtil {

    public static String get(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtil.get());
    }
}
