package com.guofang.hatch.controller;

import com.alibaba.fastjson.JSONObject;
import com.guofang.hatch.service.LoginService;
import com.guofang.hatch.util.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/auth")
    public JSONObject authLogin(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "bid, account_number, password");

        return loginService.authLogin(requestJson);
    }

    @GetMapping("/me")
    public JSONObject getInfo() {
        System.out.println("test");
        return loginService.getInfo();
    }

    @RequestMapping("/test")
    @ApiOperation("test接口")
    public String test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "admin");
        jsonObject.put("password", "123456");
        loginService.authLogin(jsonObject);
        return "test";
    }
}
