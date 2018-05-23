package com.fit.org.web.controller.plan;

import com.alibaba.fastjson.JSON;

import com.fit.org.api.model.Result;
import com.fit.org.service.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:黑绝
 * @date:2018/5/19 下午4:03
 */
@Controller
@ResponseBody
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    /**
     * http://localhost:8088/plan/count/ec6d83914e0e416ca45a51f1dda6b84b
     * @param userCode
     * @return
     */
    @RequestMapping(value="/count/{userCode}")
    public String count(@PathVariable String userCode){
        Result<Integer> result = planService.countByUserCode(userCode);
        return JSON.toJSONString(result);
    }
}
