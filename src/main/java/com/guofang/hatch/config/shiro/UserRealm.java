package com.guofang.hatch.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.guofang.hatch.service.LoginService;
import com.guofang.hatch.util.CommonUtil;
import com.guofang.hatch.util.constant.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;

import java.awt.*;
import java.util.Collection;

public class UserRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private LoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("#############  doGetAuthorizationInfo");
        Session session = SecurityUtils.getSubject().getSession();

        JSONObject permission = (JSONObject) session.getAttribute(Constant.SESSION_USER_PERMISSION);
        logger.info("permissions's value is: " + permission);
        logger.info("local permission is: " + permission.get("permissionList"));
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("#############  doGetAuthenticationInfo");
        String pidAndLoginName = (String) authenticationToken.getPrincipal();
        String[] tmp = pidAndLoginName.split("\\|");
        String bid = tmp[0];
        String loginName = tmp[1];
        JSONObject user = loginService.getUser(bid, loginName);
        String password = new String((char[]) authenticationToken.getCredentials());
        if(null == user || !CommonUtil.bcryptValidate(password, user.getString("password"))) {
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getString("account"),
                user.getString("password"),
                getName()
        );
        user.remove(password);
        SecurityUtils.getSubject().getSession().setAttribute(Constant.SESSION_USER_INFO, user);
        System.out.println(user);
        return authenticationInfo;
    }
}
