package com.fit.org.springlistener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author:黑绝
 * @date:2018/3/3 22:32
 */
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        System.out.println("==================="+event.getClass().getName());
    }
}
