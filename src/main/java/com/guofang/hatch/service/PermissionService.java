package com.guofang.hatch.service;

import com.alibaba.fastjson.JSONArray;

public interface PermissionService {
    JSONArray getUserPermission(String bid, String roleId, String identity);
}
