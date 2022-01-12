package com.nowcoder.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @ClassName CommunityUtil
 * @Description  工具类：静态方法：生成随机字符串和md5加密
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
}
