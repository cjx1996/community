package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @ClassName AlphaDaoHibernateImpl
 * @Description
 * @Author cjx
 * @Date 2022/1/8 0:10
 * @Version 1.0
 */
@Repository("alphaHibernate")
public class AlphaDaoHibernateImpl implements AlphaDao{

    @Override
    public String select() {
        return "Hibernate";
    }
}
