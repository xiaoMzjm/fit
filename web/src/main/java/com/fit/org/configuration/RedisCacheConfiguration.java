package com.fit.org.configuration;

import com.fir.org.common.FitContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author:黑绝
 * @date:2018/5/2 0:15
 */
@Configuration
@EnableCaching
public class RedisCacheConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(RedisCacheConfiguration.class);

    @Value("${spring.datasource.redis.host}")
    private String host;

    @Value("${spring.datasource.redis.port}")
    private int port;

    @Value("${spring.datasource.redis.timeout}")
    private int timeout;

    @Value("${spring.datasource.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.datasource.redis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.datasource.redis.password}")
    private String password;

    @Value("${spring.datasource.redis.username}")
    private String username;

    //@Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        if(StringUtils.isEmpty(password)) {
            password = null;
        }
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        //jedisPool.getResource();
        logger.info(FitContext.LAUNCH_LOG_PREFIX + String.format("jedisPool start success , host=%s , port=%s , username=%s , password=%s" , host , port , username , password));
        return jedisPool;
    }

    //@Bean
    public Jedis jedis(){
        Jedis jedis = new Jedis(host, port);
        jedis.auth(password);
        //jedis.get("test");
        logger.info(FitContext.LAUNCH_LOG_PREFIX + String.format("jedis start success , host=%s , port=%s , username=%s , password=%s" , host , port , username , password));
        return jedis;
    }
}
