package com.nowcoder.community.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.DigestUtils;



import java.util.Map;
import java.util.UUID;

/**
 * @ClassName CommunityUtil
 * @Description  工具类：静态方法：生成随机字符串和md5加密/生成json字符串
 * @Author cjx
 * @Date 2022/1/12 1:04
 * @Version 1.0
 */
public class CommunityUtil {

    //生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    //MD5加密
    //hello->abc123def456
    //hello + 3e48 -> abc123def456abc
    public static String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
    public static String getJSONString(int code, String msg, Map<String, Object> map){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if(map != null){
            for(String key : map.keySet()){
                json.put(key, map.get(key));
            }
        }
        return json.toString();
    }



    public static String getJSONString(int code,@Nullable String msg){
        return getJSONString(code, msg, null);
    }

    public static String getJSONString(int code){
        return getJSONString(code, null, null);
    }
}
