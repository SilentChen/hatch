package com.guofang.hatch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.guofang.hatch.dao.PermissionDao;
import com.guofang.hatch.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public JSONObject getUserPermission(String username) {
        JSONObject userPermission = getUserPermissionFromDB(username);
        return userPermission;
    }

    private JSONObject getUserPermissionFromDB(String username) {
        JSONObject userPermission = permissionDao.getUserPermission(username);
        int adminRoleId = 1;
        String roleIdKey = "roleId";
        if(adminRoleId == userPermission.getIntValue(roleIdKey)) {
            Set<String> menuList = permissionDao.getAllMenu();
            Set<String> permissionList = permissionDao.getAllPermission();
            userPermission.put("menuList", menuList);
            userPermission.put("permissionList", permissionList);
        }

        return userPermission;
    }
}
