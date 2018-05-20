package com.fit.org.web.controller.login;

import com.alibaba.fastjson.JSON;

import com.fit.org.api.model.Result;
import com.fit.org.api.model.WxLoginResultDTO;
import com.fit.org.api.model.WxUserInfoDTO;
import com.fit.org.dao.model.UserDO;
import com.fit.org.service.UserService;
import com.fit.org.service.WXService;
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

    @Autowired
    private WXService wxService;

    @RequestMapping("/getUserInfo")
    public String getUserInfo(String code , String encryptedData , String iv) {
        Result<WxUserInfoDTO> result = wxService.getUserInfo(code, encryptedData ,iv);
        return JSON.toJSONString(result);
    }


}
