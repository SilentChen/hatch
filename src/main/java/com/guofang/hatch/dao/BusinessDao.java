package com.guofang.hatch.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

public interface BusinessDao {
    JSONObject getBusiness(@Param("code") String code);
}
