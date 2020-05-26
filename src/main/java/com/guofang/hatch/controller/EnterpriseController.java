package com.guofang.hatch.controller;

import com.alibaba.fastjson.JSONObject;
import com.guofang.hatch.service.EnterpriseService;
import com.guofang.hatch.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public JSONObject list(){
        //String enterpriseName = requestJson.getString("name");

        return CommonUtil.successJson();
    }
}
