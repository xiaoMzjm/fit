package com.fit.org.web.controller.user;

import com.alibaba.fastjson.JSON;

import com.fit.org.api.model.Result;
import com.fit.org.api.model.dto.UserDTO;
import com.fit.org.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:黑绝
 * @date:2018/5/20 下午11:10
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * http://localhost:8088/user/get/ec6d83914e0e416ca45a51f1dda6b84b
     * @param userCode
     * @return
     */
    @RequestMapping(value="/get/{userCode}")
    public String get(@PathVariable String userCode) {
        Result<UserDTO> result = userService.getByCode(userCode);
        return JSON.toJSONString(result);
    }
}
