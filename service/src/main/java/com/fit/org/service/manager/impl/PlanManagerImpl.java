package com.fit.org.service.manager.impl;

import java.util.HashMap;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.fir.org.common.util.QueryUtil;
import com.fit.org.dao.mapper.PlanMapper;
import com.fit.org.dao.model.PlanDO;
import com.fit.org.service.manager.PlanManager;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:黑绝
 * @date:2018/5/19 下午3:41
 */
@Component
public class PlanManagerImpl implements PlanManager {

    @Autowired
    private PlanMapper planMapper;

    @Override
    public Integer countByUserCode(String userCode) throws Exception{
        Assert.assertNotNull("userCode is null",userCode);
        PlanDO planDO = new PlanDO();
        planDO.setUserCode(userCode);
        Map<String,Object> params = QueryUtil.transBean2Map(planDO);
        Integer result = planMapper.count(params);
        return result;
    }
}
