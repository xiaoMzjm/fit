package com.fit.org.web.controller.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSON;

import com.fit.org.api.model.User;
import com.fit.org.dao.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:黑绝
 * @date:2018/2/28 19:13
 */
@Controller
@ResponseBody
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private UserMapper userMapper;

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     * http://localhost:8088/test/hello
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        logger.info("~~~~~hello");
        System.out.println("~~~~~~system~~~~~hello");
        return "hello world";
    }

    /**
     * http://localhost:8088/test/mysqlHello
     * @return
     */
    @RequestMapping("/mysqlHello")
    public String mysqlHello(){
        List<User> result = userMapper.selectAll();
        return JSON.toJSONString(result);
    }

    /**
     * http://localhost:8088/test/transaction
     * @return
     */
    @RequestMapping("/transaction")
    public String transaction(){
        String result = transactionTemplate.execute(new TransactionCallback<String>() {

            @Override
            public String doInTransaction(TransactionStatus status) {
                return "success";
            }
        });
        return result;
    }
}
