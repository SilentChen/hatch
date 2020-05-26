package com.guofang.hatch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.guofang.hatch.dao.BusinessDao;
import com.guofang.hatch.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessDao businessDao;

    @Override
    public JSONObject getBusiness(String code) {
        return businessDao.getBusiness(code);
    }
}
