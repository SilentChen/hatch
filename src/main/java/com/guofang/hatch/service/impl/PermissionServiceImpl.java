package com.guofang.hatch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.guofang.hatch.dao.PermissionDao;
import com.guofang.hatch.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<JSONObject> getUserPermission(String bid, String roleId, String identity) {
        List<JSONObject> userPermission = getUserPermissionFromDB(bid, roleId, identity);
        return userPermission;
    }

    private List<JSONObject> getUserPermissionFromDB(String bid, String roleId, String identity) {
        List<JSONObject> permissionList;
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
