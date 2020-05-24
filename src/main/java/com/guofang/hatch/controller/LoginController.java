package com.guofang.hatch.controller;

import com.alibaba.fastjson.JSONObject;
import com.guofang.hatch.service.LoginService;
import com.guofang.hatch.util.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public JSONObject authLogin(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "bid, account_number, password");

        return loginService.authLogin(requestJson);
    }

    @GetMapping("/me")
    public JSONObject getInfo() {
        return loginService.getInfo();
    }

    @RequestMapping("/test")
    @ApiOperation("test接口")
    public JSONObject test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bid", "24");
        jsonObject.put("account_number", "admin");
        jsonObject.put("password", "e10adc3949ba59abbe56e057f20f883e");
        return loginService.authLogin(jsonObject);
    }
}
