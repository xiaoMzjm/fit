package com.fit.org.service.impl;

import java.util.*;

import com.alibaba.fastjson.JSON;

import com.fir.org.common.util.QueryUtil;
import com.fit.org.api.model.RequirementDTO;
import com.fit.org.api.model.RequirementQuery;
import com.fit.org.api.model.Result;
import com.fit.org.api.model.UserDTO;
import com.fit.org.dao.model.RequirementDO;
import com.fit.org.dao.model.UserDO;
import com.fit.org.service.RequirementService;
import com.fit.org.service.convertor.RequirementConvertor;
import com.fit.org.service.manager.RequirementManager;
import com.fit.org.service.manager.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author:黑绝
 * @date:2018/5/20 下午9:57
 */
@Component
public class RequirementServiceImpl implements RequirementService {

    private final static Logger logger = LoggerFactory.getLogger(RequirementServiceImpl.class);

    @Autowired
    private RequirementManager requirementManager;

    @Autowired
    private UserManager userManager;

    @Transactional(
        rollbackForClassName={"Exception"},
        propagation=Propagation.REQUIRED)
    @Override
    public Result<Void> changeRequirement(UserDTO userDTO, RequirementDTO requirementDTO) {
        try {

            // 更新用户信息：性别，身高，体重
            UserDO userDO = new UserDO();
            BeanUtils.copyProperties(userDTO,userDO);
            Long updateUserResult = userManager.update(userDO);
            if(updateUserResult < 1) {
                throw new RuntimeException("updateUserResult < 1");
            }

            // 更新需求
            RequirementDO requirementDO = new RequirementDO();
            BeanUtils.copyProperties(requirementDTO , requirementDO);
            Long insertRequirementResult = requirementManager.insert(requirementDO);
            if(insertRequirementResult < 1) {
                long updateRequirementResult = requirementManager.update(requirementDO);
                if(updateRequirementResult < 1) {
                    throw new RuntimeException("updateRequirementResult < 1");
                }
            }
            return Result.success(null);
        }catch (Exception e) {
            logger.error(String.format("RequirementServiceImpl#chageRequirement error,requirementDTO=%s,errorMsg=%s",
                JSON.toJSONString(requirementDTO),e.getMessage()),e);
            return Result.error("系统异常");
        }
    }

    @Override
    public Result<Long> insert(RequirementDTO requirementDTO) {
        try {
            RequirementDO requirementDO = new RequirementDO();
            BeanUtils.copyProperties(requirementDTO , requirementDO);
            Long result = requirementManager.insert(requirementDO);
            if(result < 1) {
                throw new RuntimeException("数据库返回结果<1");
            }
            return Result.success(result);
        }catch (Exception e) {
            logger.error(String.format("RequirementServiceImpl#insert error,requirementDTO=%s,errorMsg=%s",
                JSON.toJSONString(requirementDTO),e.getMessage()),e);
            return Result.error("系统异常");
        }
    }

    @Override
    public Result<List<RequirementDTO>> select(RequirementQuery requirementQuery) {
        try {
            List<RequirementDO> requirementDOList = requirementManager.select(requirementQuery);
            List<RequirementDTO> result = RequirementConvertor.converte2DTO(requirementDOList);
            return Result.success(result);
        }catch (Exception e) {
            logger.error(String.format("RequirementServiceImpl#select,requirementQuery=%s,errorMsg=%s",JSON.toJSON(requirementQuery),e.getMessage()),e);
            return Result.error("系统异常");
        }
    }

    @Override
    public Result<RequirementDTO> getByUserCode(String userCode) {
        try {
            RequirementQuery requirementQuery = new RequirementQuery();
            requirementQuery.setUserCode(userCode);
            List<RequirementDO> requirementDOList = requirementManager.select(requirementQuery);
            List<RequirementDTO> result = RequirementConvertor.converte2DTO(requirementDOList);
            RequirementDTO requirementDTO = null;
            if(!CollectionUtils.isEmpty(result)) {
                requirementDTO = result.get(0);
            }
            return Result.success(requirementDTO);
        }catch (Exception e) {
            logger.error(String.format("RequirementServiceImpl#getByUserCode,userCode=%s,errorMsg=%s",userCode,e.getMessage()),e);
            return Result.error("系统异常");
        }
    }

    @Override
    public Result<Long> update(RequirementDTO requirementDTO) {
        try {
            RequirementDO requirementDO = new RequirementDO();
            BeanUtils.copyProperties(requirementDTO,requirementDO);
            Long result = requirementManager.update(requirementDO);
            if(result < 1) {
                throw new RuntimeException("数据库返回结果<1");
            }
            return Result.success(result);
        }catch (Exception e) {
            logger.error(String.format("RequirementServiceImpl#update,requirementDTO=%s,errorMsg=%s",requirementDTO,e.getMessage()),e);
            return Result.error("系统异常");
        }
    }
}
