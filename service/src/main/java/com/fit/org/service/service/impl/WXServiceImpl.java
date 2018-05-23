package com.fit.org.service.service.impl;

import com.fit.org.api.model.Result;
import com.fit.org.api.model.dto.WxLoginResultDTO;
import com.fit.org.api.model.dto.WxUserInfoDTO;
import com.fit.org.dao.model.UserDO;
import com.fit.org.service.service.WXService;
import com.fit.org.service.manager.PlanManager;
import com.fit.org.service.manager.UserManager;
import com.fit.org.service.wapper.WxWapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:黑绝
 * @date:2018/5/19 下午10:20
 */
@Component
public class WXServiceImpl implements WXService {

    private final static Logger logger = LoggerFactory.getLogger(WXServiceImpl.class);

    @Autowired
    private WxWapper wxManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private PlanManager planManager;

    @Override
    public Result<WxUserInfoDTO> getUserInfo(String code , String encryptedData , String iv){
        try {
            WxLoginResultDTO wxLoginResultDTO = wxManager.getSessionKeyOropenid(code);
            WxUserInfoDTO result = wxManager.getUserInfo(encryptedData,wxLoginResultDTO.getSession_key(),iv);
            UserDO userDO = userManager.getByOpenId(result.getOpenId());
            if(userDO != null) {
                result.setUserCode(userDO.getCode());
            }
            else{
                UserDO insert = this.convert2UserDO(result);
                String userCode = userManager.insert(insert);
                result.setUserCode(userCode);
            }
            userDO = userManager.getByOpenId(result.getOpenId());
            Integer courseNum = planManager.countByUserCode(result.getUserCode());
            result.setCourseNum(courseNum);
            return Result.success(result);
        }catch (Exception e) {
            logger.error(String.format("WXServiceImpl#getUserInfo,encryptedData=%s,iv=%s,errorMsg=%s",encryptedData,iv,e.getMessage()),e);
            return Result.error("系统异常");
        }
    }

    private UserDO convert2UserDO(WxUserInfoDTO wxUserInfoDTO){
        UserDO userDO = new UserDO();
        userDO.setName(wxUserInfoDTO.getNickName());
        userDO.setCountry(wxUserInfoDTO.getCountry());
        userDO.setProvince(wxUserInfoDTO.getProvince());
        userDO.setOpenId(wxUserInfoDTO.getOpenId());
        return userDO;
    }
}
