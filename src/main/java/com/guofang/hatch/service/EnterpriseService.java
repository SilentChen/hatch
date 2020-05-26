package com.guofang.hatch.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface EnterpriseService {
    JSONObject listPage(JSONObject requestJson);
}
