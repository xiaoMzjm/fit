package com.fit.org.web.controller.test;

import com.fit.org.dao.mapper.UserMapper;
import com.fit.org.service.wapper.impl.RedisDistributionLockWapperImpl;
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

    @Autowired
    private RedisDistributionLockWapperImpl redisDistributionLock;

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     * http://localhost:8088/test/hello
     * https://keephealthy.duapp.com/test/hello
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        logger.info("~~~~~hello");
        System.out.println("~~~~~~system~~~~~hello");
        return "hello world";
    }

    /**
     * http://localhost:8088/test/redisLock
     * https://keephealthy.duapp.com/test/redisLock
     * @return
     */
    @RequestMapping("/redisLock")
    public String redisLock() throws Exception{
        String value = redisDistributionLock.lock("haha");
        Thread.sleep(10000);
        redisDistributionLock.unlock("haha" , value);
        return "success";
    }

    /**
     * http://localhost:8088/test/redisLockLimitTime?limitTime=2000
     * https://keephealthy.duapp.com/test/redisLockLimitTime?limitTime=2000
     * @return
     */
    @RequestMapping("/redisLockLimitTime")
    public String redisLockLimitTime(Long limitTime) throws Exception{
        String value = redisDistributionLock.lockLimitTime("haha" , limitTime);
        redisDistributionLock.unlock("haha" , value);
        return "success";
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
                if(true) {
                    throw new RuntimeException("haha");
                }
                return "success";
            }
        });
        return result;
    }

    /**
     * 嵌套一个子事物，子事物抛异常。
     * http://localhost:8088/test/transactionqiantao
     * @return
     */
    @RequestMapping("/transactionqiantao")
    public String transactionqiantao(){
        String result = transactionTemplate.execute(new TransactionCallback<String>() {

            @Override
            public String doInTransaction(TransactionStatus status) {
                // 子事物
                innerTransaction();
                return "success";
            }
        });
        return result;
    }

    public void innerTransaction(){
        String result = transactionTemplate.execute(new TransactionCallback<String>() {

            @Override
            public String doInTransaction(TransactionStatus status) {
                if(true) {
                    throw new RuntimeException("haha");
                }
                return "success";
            }
        });
    }
}
