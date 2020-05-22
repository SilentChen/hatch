package com.guofang.hatch.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.Set;

public interface PermissionDao {
    JSONObject getUserPermission(String roleId);

    Set<String> getAllMenu();

    Set<String> getAllPermission();
}
