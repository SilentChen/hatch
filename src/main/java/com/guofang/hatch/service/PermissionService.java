package com.guofang.hatch.service;

import com.alibaba.fastjson.JSONObject;

public interface PermissionService {
    JSONObject getUserPermission(String username);
}
