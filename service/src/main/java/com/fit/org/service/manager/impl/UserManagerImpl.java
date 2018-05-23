package com.fit.org.service.manager.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fir.org.common.exception.CustomerException;
import com.fir.org.common.util.QueryUtil;
import com.fir.org.common.util.UUIDUtil;
import com.fit.org.api.model.query.UserQuery;
import com.fit.org.dao.mapper.UserMapper;
import com.fit.org.dao.model.UserDO;
import com.fit.org.service.manager.UserManager;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author:黑绝
 * @date:2018/5/19 下午12:21
 */
@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserMapper userMapper;

    public String insert(UserDO userDO) throws Exception {
        Assert.assertNotNull("userDO is null" , userDO);
        Assert.assertNotNull("name is null",userDO.getName());
        Assert.assertNotNull("openId is null" , userDO.getOpenId());
        String code = UUIDUtil.get();
        userDO.setCode(code);
        userDO.setGmtCreate(new Date());
        userDO.setGmtModified(new Date());
        Long result = userMapper.insert(userDO);
        return result > 0 ? code : null;
    }

    @Override
    public List<UserDO> select(UserQuery userQuery) throws Exception{
        Assert.assertNotNull("userQuery is null" , userQuery);
        Map<String,Object> params = QueryUtil.transBean2Map(userQuery);
        List<UserDO> result = userMapper.select(params);
        return result;
    }

    @Override
    public UserDO getByOpenId(String openId) throws Exception {
        Assert.assertNotNull("openId is null" , openId);
        UserDO userDO = new UserDO();
        userDO.setOpenId(openId);
        Map<String,Object> params = QueryUtil.transBean2Map(userDO);
        List<UserDO> result = userMapper.select(params);
        return CollectionUtils.isEmpty(result) ? null : result.get(0);

    }

    @Override
    public UserDO getByCode(String code) throws Exception {
        Assert.assertNotNull("code is null" , code);
        UserDO userDO = new UserDO();
        userDO.setCode(code);
        Map<String,Object> params = QueryUtil.transBean2Map(userDO);
        List<UserDO> result = userMapper.select(params);
        return CollectionUtils.isEmpty(result) ? null : result.get(0);
    }

    @Override
    public Long update(UserDO userDO) throws Exception {
        Assert.assertNotNull("userDO is null" , userDO);
        Assert.assertNotNull("code is null" , userDO.getCode());
        Assert.assertNotNull("weight is null" , userDO.getWeight());
        Assert.assertNotNull("height is null" , userDO.getHeight());
        if(userDO.getWeight() < 1) {
            throw new CustomerException("有点皮,重量不能小于1kg喔");
        }
        if(userDO.getWeight() > 500) {
            throw new CustomerException("有点皮,重量不能大于500kg喔");
        }
        if(userDO.getHeight() < 1) {
            throw new CustomerException("有点皮,身高不能小于1cm喔");
        }
        if(userDO.getHeight() > 300) {
            throw new CustomerException("有点皮,身高不能大于300cm喔");
        }
        // 保留小数点后1位数
        int   scale  =   1;
        int   roundingMode  =  4;
        BigDecimal weight  =   new  BigDecimal(userDO.getWeight());
        weight   =  weight.setScale(scale,roundingMode);
        userDO.setWeight(weight.floatValue());
        BigDecimal height  =   new  BigDecimal(userDO.getHeight());
        height   =  height.setScale(scale,roundingMode);
        userDO.setHeight(height.floatValue());

        Map<String,Object> params = QueryUtil.transBean2Map(userDO);
        Long result = userMapper.update(params);
        return result;
    }
}
