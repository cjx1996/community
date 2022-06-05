package com.nowcoder.community.util;

/**
 * @ClassName RedisKeyUtil
 * @Description
 * @Author cjx
 * @Date 2022/4/25 21:23
 * @Version 1.0
 */
public class RedisKeyUtil {
    private static final String SPLIT=":";
    private static final String PREFIX_ENTITY_LIKE="like:entity";
    private static final String PREFIX_USER_LIKE="like:user";

    //生成某个实体的赞
    //like:entity:entityType:entityId->set(UserId)
    public static String getEntityLikeKey(int entityType,int entityId){
        return PREFIX_ENTITY_LIKE+SPLIT+entityType+SPLIT+entityId;
    }

    //某个用户的赞
    //like:user:userID->int
    public static String getUserLikeKey(int userId){
        return PREFIX_USER_LIKE+SPLIT +userId;
    }


}
