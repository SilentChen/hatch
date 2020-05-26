package com.guofang.hatch.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao {
    int insert(@Param("table") String table,
               @Param("columns") String columns,
               @Param("values")List<JSONObject> values);
}