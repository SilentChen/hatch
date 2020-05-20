package com.guofang.hatch.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

public interface LoginDao {
    JSONObject getUser(@Param("bid") String bid,  @Param("username") String username, @Param("password") String password);
}
