package com.fit.org.service;

import com.fit.org.api.model.Result;
import com.fit.org.dao.model.PlanDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:黑绝
 * @date:2018/5/19 下午3:55
 */
public interface PlanService {

    /**
     * 统计一个用户参与了多少教程
     * @param userCode
     * @return
     */
    Result<Integer> countByUserCode(String userCode);
}
