package com.fit.org.service.manager.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.*;

import com.fir.org.common.util.QueryUtil;
import com.fit.org.api.model.dto.RequirementDTO;
import com.fit.org.api.model.query.RequirementQuery;
import com.fit.org.dao.mapper.RequirementMapper;
import com.fit.org.dao.model.RequirementDO;
import com.fit.org.dao.model.UserDO;
import com.fit.org.service.manager.RequirementManager;
import com.fit.org.service.manager.UserManager;
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
    @Autowired
    private UserManager usermanager;

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

    @Override
    public Float calTargetNum(RequirementDTO requirementDTO) throws Exception {
        if(requirementDTO == null) {
            return 0F;
        }
        UserDO userDO = usermanager.getByCode(requirementDTO.getUserCode());
        Assert.assertNotNull(String.format("查询用户失败,code=%s",requirementDTO.getUserCode()) , userDO);
        Float weight = userDO.getWeight();

        // 重量 * 0.05，例如100kg，那么一个月减肥目标为5kg
        Float target = weight * 0.03F ;

        // 再乘以难度系数
        Integer difficulty = requirementDTO.getDifficulty();

        // 新手，乘以0.5倍
        if(difficulty == 1) {
            target = target * 0.8F;
        }

        // 入门，不变
        else if(difficulty == 2) {
            target = target;
        }

        // pro，乘以1.5倍
        else if(difficulty == 3) {
            target = target * 1.2F;
        }

        // 保留小数点后1位数
        int   scale  =   1;
        int   roundingMode  =  4;
        BigDecimal bd  =   new  BigDecimal(target);
        bd   =  bd.setScale(scale,roundingMode);
        target   =  bd.floatValue();
        return target;
    }
}
