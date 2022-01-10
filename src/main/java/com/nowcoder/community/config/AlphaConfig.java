package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @ClassName AlphaConfig
 * @Description
 * @Author cjx
 * @Date 2022/1/8 0:37
 * @Version 1.0
 */
@Configuration
public class AlphaConfig {
    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd:mm:ss");
    }
}
