package com.guofang.hatch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.guofang.hatch.dao.LoginDao;
import com.guofang.hatch.service.LoginService;
import com.guofang.hatch.service.PermissionService;
import com.guofang.hatch.util.CommonUtil;
import com.guofang.hatch.util.constant.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private PermissionService permissionService;

    @Override
    public JSONObject authLogin(JSONObject jsonObject) {
        String bid = jsonObject.getString("bid");
        String username = jsonObject.getString("account_number");
        String password = jsonObject.getString("password");
        JSONObject data = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(bid + "|" + username, password);
        try {
            currentUser.login(token);
            data.put("status", 1);
        }catch (AuthenticationException e){
            data.put("status", 0);
        }

        return CommonUtil.successJson(data);
    }

    @Override
    public JSONObject getUser(String bid, String username) {
        return loginDao.getUser(bid, username);
    }

    @Override
    public JSONObject getInfo() {
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constant.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        JSONObject info = new JSONObject();
        JSONObject userPermission = permissionService.getUserPermission(username);
        session.setAttribute(Constant.SESSION_USER_PERMISSION, userPermission);
        info.put("userPermission", userInfo);
        return CommonUtil.successJson(info);
    }

    @Override
    public JSONObject logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
        }
        return CommonUtil.successJson();
    }

}
