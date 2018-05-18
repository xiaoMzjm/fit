package com.fit.org.web.controller.login;

import com.alibaba.fastjson.JSON;

import com.fit.org.api.model.Result;
import com.fit.org.api.model.WxLoginResultDTO;
import com.fit.org.api.model.WxUserInfoDTO;
import com.fit.org.service.manager.impl.WxManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:黑绝
 * @date:2018/4/29 21:54
 */
@Controller
@ResponseBody
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private WxManagerImpl wxManager;

    @RequestMapping("/getUserInfo")
    public String getUserInfo(String code , String encryptedData , String iv) {

        try {

            WxLoginResultDTO wxLoginResultDTO = wxManager.getSessionKeyOropenid(code);
            WxUserInfoDTO wxUserInfoDTO = wxManager.getUserInfo(encryptedData, wxLoginResultDTO.getSession_key(), iv);

            return JSON.toJSONString(Result.success(wxUserInfoDTO));
        }catch (Exception e) {
            String errMsg = String.format("getUserInfo error , code=%s , errMsg=%s",code,e.getMessage());
            logger.error(errMsg , e);
            return JSON.toJSONString(Result.error(errMsg));
        }
    }
}
