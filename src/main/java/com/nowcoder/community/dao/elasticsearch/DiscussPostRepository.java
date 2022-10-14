package com.nowcoder.community.dao.elasticsearch;

import com.nowcoder.community.entity.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName DiscussPostRepository
 * @Description 继承ElasticSearch自带的接口就可以，同时定义泛型，第一个参数为将要存储的类型，第二个参数为id的类型
 * 父接口中已经定义好了对es服务器访问的各种增删改查的方法
 * @Author cjx
 * @Date 2022/10/13 22:58
 * @Version 1.0
 */

@Repository
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost,Integer> {

}
