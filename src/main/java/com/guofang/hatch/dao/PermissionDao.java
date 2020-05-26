package com.guofang.hatch.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;

public interface PermissionDao {
    List<JSONObject> getUserPermission(String roleId);

    Set<String> getAllMenu();

    List<JSONObject> getAllPermission(String bid);
}
