package com.nowcoder.community.util;

/**
 * @ClassName RedisKeyUtil
 * @Description
 * @Author cjx
 * @Date 2022/4/25 21:23
 * @Version 1.0
 */
public class RedisKeyUtil {
    /**
     * 间隔字符,":"
     */
    private static final String SPLIT=":";
    /**
     * 对帖子赞的key的头部,"like:entity"
     */
    private static final String PREFIX_ENTITY_LIKE="like:entity";
    /**
     * 统计每个人的获赞次数的头部,"like:user"
     */
    private static final String PREFIX_USER_LIKE="like:user";

    /**
     * 表示被关注的目标，"FOLLOWEE"
     */
    private static final String PREFIX_FOLLOWEE="followee";
    /**
     * 表示关注者，或者粉丝,"FOlLOWER"
     */
    private static final String PREFIX_FOLLOWER="follower";


    /**
     *  生成某个实体的赞
     *  like:entity:entityType:entityId->set(UserId)
     * @param entityType
     * @param entityId
     * @return
     */
    public static String getEntityLikeKey(int entityType,int entityId){
        return PREFIX_ENTITY_LIKE+SPLIT+entityType+SPLIT+entityId;
    }


    /**
     * 某个用户的赞
     * like:user:userID->int
     * @param userId
     * @return
     */
    public static String getUserLikeKey(int userId){

        return PREFIX_USER_LIKE+SPLIT +userId;
    }

    /**
     * 某个用户关注的实体
     * followee:userId:entityType->zset(entityId,now)
     * @param userId
     * @param entityType
     * @return
     */
    public static String getFolloweeKey(int userId,int entityType){
        return PREFIX_FOLLOWEE+SPLIT+userId+SPLIT+entityType;
    }

    /**
     * 某个实体拥有的粉丝
     * follower:entityType:entityId->zset(userId,now)
     * @param entityType
     * @param entityId
     * @return
     */
    public static String getFollowerKey(int entityType,int entityId){
        return PREFIX_FOLLOWER+SPLIT+entityType+SPLIT+entityId;
    }

}
