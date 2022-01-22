package com.nowcoder.community.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CookieUtil
 * @Description
 * @Author cjx
 * @Date 2022/1/20 16:47
 * @Version 1.0
 */
public class CookieUtil {

    public static String getValue(HttpServletRequest request,String name){
        if(request==null||name==null){
            throw new IllegalArgumentException("参数为空");
        }
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
