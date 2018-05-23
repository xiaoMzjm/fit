package com.fit.org.service.wapper;

/**
 * @author:黑绝
 * @date:2018/5/1 2:24
 */
public interface DistributionLockWapper {

    /**
     * 加锁
     * @param lockKey       key
     * @return  加锁时间
     */
    String lock(String lockKey) throws Exception;

    /**
     * 加锁，limitTime 毫秒后如果还没加锁成功，则抛异常
     * @param lockKey
     * @param timeout
     * @return
     */
    String lockLimitTime(String lockKey , Long timeout) throws Exception;

    /**
     * 需要更加加锁时间判断是否有权限
     * @param lockKey
     * @param requireId
     */
    void unlock(String lockKey, String requireId);
}
