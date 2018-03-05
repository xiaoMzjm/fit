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

    @RequestMapping("/mysqlHello2")
    protected void doGet()
        throws ServletException, IOException {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;

        try {
            /*****填写数据库相关信息(请查找数据库详情页)*****/
            String databaseName = "UWrmSuNCUzQExufPUpkA";
            String host = "sqld.duapp.com";
            String port = "4050";
            String username = "ae5f7053ab1842e19e46f1657694c420";
            String password = "3b437259315243beb1540d963dd8b2f0";
            String driverName = "com.mysql.jdbc.Driver";
            String dbUrl = "jdbc:mysql://";
            String serverName = host + ":" + port + "/";
            String connName = dbUrl + serverName + databaseName;

            /******接着连接并选择数据库名为databaseName的服务器******/
            Class.forName(driverName);
            connection = DriverManager.getConnection(connName, username,
                password);
            stmt = connection.createStatement();
            /******至此连接已完全建立，就可对当前数据库进行相应的操作了*****/
            /******接下来就可以使用其它标准mysql函数操作进行数据库操作*****/
            //创建一个数据库表
            sql = "select * from user limit 2";
            stmt.execute(sql);
            ResultSet resultSet = stmt.getResultSet();
            String name = resultSet.getString("name");
            System.out.println("==============resultSet" + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
