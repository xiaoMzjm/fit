package com.fit.org.service.manager.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fit.org.service.manager.DistributionLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author:黑绝
 * @date:2018/5/1 2:24
 */
@Component
public class RedisDistributionLockImpl implements DistributionLock {

    /**
     * 加锁时间，3秒过期自动释放
     */
    private final static Integer LOCK_TIMEOUT = 15000;

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    @Resource
    private JedisPool jedisPool;

    private final static JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();

    @Override
    public String lock(String lockKey) throws Exception{
        String requestId = UUID.randomUUID().toString();
        Jedis jedis = jedisPool.getResource();
        this.lock(jedis , lockKey , requestId , LOCK_TIMEOUT , null);
        return requestId;
    }

    @Override
    public String lockLimitTime(String lockKey, Long timeout) throws Exception {
        String requestId = UUID.randomUUID().toString();
        this.lock(jedisPool.getResource() , lockKey , requestId , LOCK_TIMEOUT , timeout);
        return requestId;
    }

    @Override
    public void unlock(String lockKey,String requireId) {
        this.unlock(jedisPool.getResource() , lockKey , requireId);
    }

    private void lock(Jedis jedis, String lockKey, String requestId, int expireTime , Long timeout) throws Exception {

        Long timeCount = 0L;
        while (true) {
            String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

            // 加锁成功
            if (LOCK_SUCCESS.equals(result)) {
                return ;
            }

            Thread.sleep(100);
            timeCount += 100;
            if(timeout != null && timeCount >= timeout) {
                throw new RuntimeException(String.format("redis lock timeout (%sms) , lockKey=%s , requestId=%s," , timeout , lockKey , requestId));
            }
        }
    }

    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public void unlock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return ;
        }
        throw new RuntimeException(String.format("releaseDistributedLock fail , lockKey=%s , requestId=%s" , lockKey , requestId));

    }

}
