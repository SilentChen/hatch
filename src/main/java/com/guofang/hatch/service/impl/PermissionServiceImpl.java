package com.guofang.hatch.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.guofang.hatch.dao.PermissionDao;
import com.guofang.hatch.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public JSONArray getUserPermission(String bid, String roleId, String identity) {
        JSONArray userPermission = getUserPermissionFromDB(bid, roleId, identity);
        return userPermission;
    }

    private JSONArray getUserPermissionFromDB(String bid, String roleId, String identity) {
        JSONArray permissionList;
        // super admin
        if(identity.equals("1")) {
            permissionList = permissionDao.getAllPermission(bid);
        // normal admin
        }else{
            permissionList = permissionDao.getUserPermission(roleId);
        }

        return permissionList;
    }
}
