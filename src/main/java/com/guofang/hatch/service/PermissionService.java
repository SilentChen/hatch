package com.guofang.hatch.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface PermissionService {
    List<JSONObject> getUserPermission(String bid, String roleId, String identity);
}
