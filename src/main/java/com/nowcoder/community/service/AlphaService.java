package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName AlphaService
 * @Description
 * @Author cjx
 * @Date 2022/1/8 0:22
 * @Version 1.0
 */
@Service
//@Scope("prototype") 这是设置为多例模式，一般不用设置，默认为单例模式
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;
//    public AlphaService(){
//        System.out.println("实例化AlphaService");
//    }
//    @PostConstruct
//    public void init(){
//        System.out.println("初始化AlphaService");
//    }
//    @PreDestroy
//    public void destory(){
//        System.out.println("销毁AlphaService");
//    }
    public String find(){
        String ans = alphaDao.select();
        return ans;
    }
}
