package com.fit.org.web.controller.requirement;

import com.alibaba.fastjson.JSON;

import com.fit.org.api.model.RequirementDTO;
import com.fit.org.api.model.Result;
import com.fit.org.api.model.UserDTO;
import com.fit.org.service.RequirementService;
import com.fit.org.service.UserService;
import com.fit.org.service.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:黑绝
 * @date:2018/5/20 下午3:44
 */
@Controller
@ResponseBody
@RequestMapping("/requirement")
public class RequirementController {

    @Autowired
    private RequirementService requirementService;

    /**
     * http://localhost:8088/requirement/requirement
     * @param userDTO
     * @param requirementDTO
     * @return
     */
    @RequestMapping(value="/changeRequirement")
    public String changeRequirement(UserDTO userDTO, RequirementDTO requirementDTO){
        Result<Void> result = Result.success(null);
        Result<Void> updateUser = requirementService.changeRequirement(userDTO,requirementDTO);
        if(!updateUser.getSuccess()) {
            return JSON.toJSONString(updateUser);
        }
        return JSON.toJSONString(result);
    }

    /**
     * http://localhost:8088/requirement/get/xxx
     * @param userCode
     * @return
     */
    @RequestMapping(value="/get/{userCode}")
    public String get(@PathVariable String userCode){
        Result<RequirementDTO> result = requirementService.getByUserCode(userCode);
        return JSON.toJSONString(result);
    }
}
