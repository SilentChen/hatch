package com.guofang.hatch.dao;

import com.alibaba.fastjson.JSONArray;
import java.util.Set;

public interface PermissionDao {
    JSONArray getUserPermission(String roleId);

    Set<String> getAllMenu();

    JSONArray getAllPermission(String bid);
}
