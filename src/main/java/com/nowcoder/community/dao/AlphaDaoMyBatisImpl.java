package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @ClassName AlphaDaoMyBatisImpl
 * @Description
 * @Author cjx
 * @Date 2022/1/8 0:14
 * @Version 1.0
 */
@Repository
@Primary
public class AlphaDaoMyBatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "MyBatis";
    }
}
