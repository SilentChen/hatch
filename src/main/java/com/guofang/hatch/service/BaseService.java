package com.guofang.hatch.service;

import com.alibaba.fastjson.JSONObject;

public interface BaseService {
    String getDbTable();

    String getFillableColumns();
}
