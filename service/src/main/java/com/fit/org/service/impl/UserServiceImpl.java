package com.fit.org.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;

import com.fir.org.common.exception.CustomerException;
import com.fit.org.api.model.Result;
import com.fit.org.api.model.UserDTO;
import com.fit.org.api.model.UserQuery;
import com.fit.org.dao.model.UserDO;
import com.fit.org.service.UserService;
import com.fit.org.service.convertor.UserConvertor;
import com.fit.org.service.manager.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:黑绝
 * @date:2018/5/19 下午12:21
 */
@Component
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserManager userManager;

    public Result<String> insert(UserDTO userDTO){
        try {
            UserDO userDO = new UserDO();
            BeanUtils.copyProperties(userDTO , userDO);
            String result = userManager.insert(userDO);
            return Result.success(result);
        }catch (Exception e) {
            logger.error(String.format("UserServiceImpl#insert , userDTO=%s , errorMsg=%s ", JSON.toJSONString(userDTO) , e.getMessage()),e);
            return Result.error("系统异常");
        }
    }

    @Override
    public Result<List<UserDTO>> select(UserQuery userQuery) {
        try {
            List<UserDO> userDOList = userManager.select(userQuery);
            List<UserDTO> result = UserConvertor.converte2DTO(userDOList);
            return Result.success(result);
        }catch (Exception e) {
            logger.error(String.format("sUserServiceImpl#select,userQuery=%s,errorMsg=%s", JSON.toJSONString(userQuery),e.getMessage()),e);
            return Result.error("系统异常");
        }
    }

    @Override
    public Result<UserDTO> getByCode(String code) {
        try {
            UserDO userDO = userManager.getByCode(code);
            UserDTO result = UserConvertor.converte2DTO(userDO);
            return Result.success(result);
        }catch (Exception e) {
            logger.error(String.format("sUserServiceImpl#getByCode,code=%s,errorMsg=%s", code,e.getMessage()),e);
            return Result.error("系统异常");
        }
    }

    @Override
    public Result<Long> update(UserDTO userDTO) {
        try {
            UserDO get = userManager.getByCode(userDTO.getCode());
            if(get == null) {
                throw new CustomerException("用户不存在");
            }
            UserDO userDO = new UserDO();
            BeanUtils.copyProperties(userDTO , userDO);
            Long result = userManager.update(userDO);
            if(result < 1) {
                throw new RuntimeException("数据库更新结果<1");
            }
            return Result.success(result);
        }catch (CustomerException ce){
            logger.error(String.format("UserServiceImpl#update , userDTO=%s , errorMsg=%s ", JSON.toJSONString(userDTO) , ce.getMessage()),ce);
            return Result.error(ce.getMessage());
        }catch (Exception e) {
            logger.error(String.format("UserServiceImpl#update , userDTO=%s , errorMsg=%s ", JSON.toJSONString(userDTO) , e.getMessage()),e);
            return Result.error("系统异常");
        }
    }
}
