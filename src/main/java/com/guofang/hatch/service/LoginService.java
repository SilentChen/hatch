package com.guofang.hatch.service;

import com.alibaba.fastjson.JSONObject;

public interface LoginService {
    JSONObject authLogin(JSONObject jsonObject);

    JSONObject getUser(String bid, String username);

    JSONObject getInfo();

    JSONObject logout();
}
