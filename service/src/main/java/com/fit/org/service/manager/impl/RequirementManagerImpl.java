package com.fit.org.service.manager.impl;

import java.util.Date;
import java.util.*;

import com.fir.org.common.util.QueryUtil;
import com.fit.org.api.model.RequirementQuery;
import com.fit.org.dao.mapper.RequirementMapper;
import com.fit.org.dao.model.RequirementDO;
import com.fit.org.dao.model.UserDO;
import com.fit.org.service.manager.RequirementManager;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:黑绝
 * @date:2018/5/20 下午5:48
 */
@Component
public class RequirementManagerImpl implements RequirementManager {

    @Autowired
    private RequirementMapper requirementMapper;

    @Override
    public Long insert(RequirementDO requirementDO) throws Exception {
        Assert.assertNotNull("requirementDO is null" , requirementDO);
        Assert.assertNotNull("userCode is null" , requirementDO.getUserCode());
        Assert.assertNotNull("requirementType is null" , requirementDO.getRequirementType());
        Assert.assertNotNull("difficulty is null" , requirementDO.getDifficulty());
        requirementDO.setGmtCreate(new Date());
        requirementDO.setGmtModified(new Date());
        Long result = requirementMapper.insert(requirementDO);
        return result;
    }

    @Override
    public List<RequirementDO> select(RequirementQuery requirementQuery) throws Exception {
        Assert.assertNotNull("requirementQuery is null" , requirementQuery);
        Assert.assertNotNull("userCode is null" , requirementQuery.getUserCode());
        Map<String,Object> params = QueryUtil.transBean2Map(requirementQuery);
        List<RequirementDO> result = requirementMapper.select(params);
        return result;
    }

    @Override
    public Long update(RequirementDO requirementDO) throws Exception {
        Assert.assertNotNull("requirementDO is null" , requirementDO);
        Assert.assertNotNull("userCode is null" , requirementDO.getUserCode());
        Map<String,Object> params = QueryUtil.transBean2Map(requirementDO);
        Long result = requirementMapper.update(params);
        return result;
    }
}
