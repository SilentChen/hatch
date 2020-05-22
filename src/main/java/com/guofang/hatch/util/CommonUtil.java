package com.guofang.hatch.util;

import com.alibaba.fastjson.JSONObject;
import com.guofang.hatch.config.exception.CommonJsonException;
import com.guofang.hatch.util.constant.Constant;
import com.guofang.hatch.util.constant.ErrorEnum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

public class CommonUtil {
    public static JSONObject successJson() {
        return successJson(new JSONObject());
    }

    /**
     * @description
     * @param obj
     * @return
     */
    public static JSONObject successJson(Object obj) {
        JSONObject resultJson = new JSONObject();
        resultJson.put(Constant.JSON_RESPONSE_CODE, Constant.SUCCESS_CODE);
        resultJson.put(Constant.JSON_RESPONSE_MESSAGE, Constant.SUCCESS_MSG);
        resultJson.put(Constant.JSON_RESPONSE_DATA, obj);

        return resultJson;
    }

    /**
     * @description
     * @param errorEnum
     * @return
     */
    public static JSONObject errorJson(ErrorEnum errorEnum) {
        JSONObject resultJson = new JSONObject();
        resultJson.put(Constant.JSON_RESPONSE_CODE, errorEnum.getErrorCode());
        resultJson.put(Constant.JSON_RESPONSE_MESSAGE, errorEnum.getErrorMsg());
        resultJson.put(Constant.JSON_RESPONSE_DATA, new JSONObject());

        return resultJson;
    }

    /**
     * @description
     * @param requestJson
     * @param list
     * @param totalCount
     * @return
     */
    public static JSONObject successJsonPage(final JSONObject requestJson, List<JSONObject> list, int totalCount) {
        int perPage     = requestJson.getIntValue("per_page");
        int currentPage = requestJson.getIntValue("current_page");
        int totalPage   = totalCount > 0 ? (totalCount % perPage > 0 ? totalCount / perPage + 1 : totalCount / perPage) : 1;
        JSONObject resultJson = successJson();
        resultJson.put("data", list);
        resultJson.put("per_page", perPage);
        resultJson.put("total", totalCount);
        requestJson.put("current_page", currentPage);

        return resultJson;
    }

    /**
     * @description
     * @param list
     * @param totalPage
     * @param perPage
     * @param currentPage
     * @return
     */
    public static JSONObject successJsonPage(List<JSONObject> list, int totalPage, int perPage, int currentPage) {
        JSONObject resultJson = successJson();
        resultJson.put("data", list);
        resultJson.put("total", totalPage);
        resultJson.put("per_page", perPage);
        resultJson.put("current_page", currentPage);

        return resultJson;
    }

    /**
     * @description
     * @param request
     * @return
     */
    public static JSONObject request2Json(HttpServletRequest request) {
        JSONObject requestJson = new JSONObject();
        Enumeration paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] pv = request.getParameterValues(paramName);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i < pv.length; i++) {
                if(i>0) sb.append(",");
                sb.append(pv[i]);
            }
            requestJson.put(paramName, sb.toString());
        }

        return requestJson;
    }

    /**
     * @description
     * @param jsonObject
     * @param requiredColumns
     */
    public static void hasAllRequired(final JSONObject jsonObject, String requiredColumns) {
        if(!StringTool.isNullOrEmpty(requiredColumns)) {
            String[] columns = requiredColumns.split(",");
            String missCol = "";
            for(String column : columns) {
                Object val = jsonObject.get(column.trim());
                if(!StringTool.isNullOrEmpty(val)) {
                    missCol += column + " ";
                }
            }
            if(StringTool.isNullOrEmpty(missCol)) {
                jsonObject.clear();;
                jsonObject.put(Constant.JSON_RESPONSE_CODE, ErrorEnum.E_10001.getErrorCode());
                jsonObject.put(Constant.JSON_RESPONSE_MESSAGE, ErrorEnum.E_10001.getErrorMsg() + missCol.trim());
                jsonObject.put(Constant.JSON_RESPONSE_DATA, new JSONObject());
                throw new CommonJsonException(jsonObject);
            }
        }
    }

    /**
     * @description
     * @param request
     * @param requiredColumns
     * @return
     */
    public static JSONObject convert2JsonAndCheckRequiredColumns(HttpServletRequest request, String requiredColumns) {
        JSONObject jsonObject = request2Json(request);
        hasAllRequired(jsonObject, requiredColumns);

        return jsonObject;
    }

    /**
     * @description
     * @param pwdString
     * @return
     */
    public static String md5(String pwdString){
        return DigestUtils.md5DigestAsHex(pwdString.getBytes());
    }

    /**
     * description
     * @param pwdString
     * @return
     */
    public static String bcrypt(String pwdString) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pwdString);
    }

    /**
     * description
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    public static boolean bcryptValidate(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }
}
