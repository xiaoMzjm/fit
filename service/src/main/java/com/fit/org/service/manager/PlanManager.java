package com.fit.org.service.manager;

/**
 * @author:黑绝
 * @date:2018/5/19 下午3:40
 */
public interface PlanManager {

    /**
     * 统计一个用户订阅的教程数量
     * @param userCode
     * @return
     */
    Integer countByUserCode(String userCode) throws Exception;
}
