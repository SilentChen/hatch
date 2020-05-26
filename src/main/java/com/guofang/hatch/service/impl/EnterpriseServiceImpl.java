package com.guofang.hatch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.guofang.hatch.dao.EnterpriseDao;
import com.guofang.hatch.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    private EnterpriseDao enterpriseDao;

    @Override
    public JSONObject listPage(JSONObject request){
        return new JSONObject();
    }
}
