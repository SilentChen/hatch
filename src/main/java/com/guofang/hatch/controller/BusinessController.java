package com.guofang.hatch.controller;

import com.alibaba.fastjson.JSONObject;
import com.guofang.hatch.service.BusinessService;
import com.guofang.hatch.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @PostMapping("/business")
    public JSONObject business(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "code");
        return CommonUtil.successJson(businessService.getBusiness(requestJson.getString("code")));
    }
}
