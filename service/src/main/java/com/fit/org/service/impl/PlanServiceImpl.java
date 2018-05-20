package com.fit.org.service.impl;

import com.fit.org.api.model.Result;
import com.fit.org.service.PlanService;
import com.fit.org.service.manager.PlanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:黑绝
 * @date:2018/5/19 下午3:56
 */
@Component
public class PlanServiceImpl implements PlanService {

    private final static Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);

    @Autowired
    private PlanManager planManager;

    @Override
    public Result<Integer> countByUserCode(String userCode) {
        try {
            Integer result = planManager.countByUserCode(userCode);
            return Result.success(result);
        }catch (Exception e) {
            logger.error(String.format("countByUserCode error, userCode=%s, errorMsg=%s",userCode,e.getMessage()),e);
            return Result.error("系统异常");
        }
    }
}
