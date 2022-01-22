package com.nowcoder.community.util;

import com.nowcoder.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * @ClassName HostHolder
 * @Description 持有用户信息，用于代替session对象
 * @Author cjx
 * @Date 2022/1/20 16:56
 * @Version 1.0
 */

@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<User>();

    public void setUser(User user){
        users.set(user);
    }
    public User getUser(){
        return users.get();
    }

    public void clear(){
        users.remove();
    }



}
