package com.nowcoder.community.controller;

import com.nowcoder.community.annotation.LoginRequired;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.FollowService;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName FollowController
 * @Description
 * @Author cjx
 * @Date 2022/6/7 23:18
 * @Version 1.0
 */
@Controller
public class FollowController {

    @Autowired
    private FollowService followService;

    @Autowired
    private HostHolder hostHolder;

    /**
     * 关注功能实现
     * @param entityType
     * @param entityId
     * @return
     */
    @RequestMapping(path="/follow",method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String follow(int entityType,int entityId){
        User user = hostHolder.getUser();

        followService.follow(user.getId(),entityType,entityId);

        return CommunityUtil.getJSONString(0,"已关注！");
    }

    /**
     * 取消关注功能实现
     * @param entityType
     * @param entityId
     * @return
     */
    @RequestMapping(path="/unfollow",method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String unfollow(int entityType,int entityId){
        User user = hostHolder.getUser();

        followService.unfollow(user.getId(),entityType,entityId);

        return CommunityUtil.getJSONString(0,"已取消关注！");
    }
}
