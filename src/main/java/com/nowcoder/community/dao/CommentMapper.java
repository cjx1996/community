package com.nowcoder.community.dao;

import com.nowcoder.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName CommentMapper
 * @Description
 * @Author cjx
 * @Date 2022/2/13 17:42
 * @Version 1.0
 */
@Mapper
public interface CommentMapper {

    List<Comment> selectCommentsByEntity(int entityType,int entityId,int offset,int limit);

    int selectCountByEntity(int entityType,int entityId);

    int insertComment(Comment comment);

    Comment selectCommentById(int id);
}
