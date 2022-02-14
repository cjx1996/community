package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @ClassName DiscussPostMapper
 * @Description
 * @Author cjx
 * @Date 2022/1/9 23:17
 * @Version 1.0
 */
@Mapper
public interface DiscussPostMapper {


    /**
     *将来个人首页功能使用userId查询个人帖子,动态拼接url
     * @param userId 用户id，可以不传
     * @param offset 起始行号
     * @param limit 每页条数限制
     * @return
     */
    List<DiscussPost> selectDiscussPosts( int userId, int offset, int limit);

    //@Param注解用于给参数取别名
    //如果只有一个参数，并且在<if>里使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);

    int updateCommentCount(int id,int commentCount);
}
