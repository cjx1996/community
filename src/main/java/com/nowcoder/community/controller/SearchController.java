package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.service.ElasticsearchService;
import com.nowcoder.community.service.LikeService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SearchController
 * @Description
 * @Author cjx
 * @Date 2022/10/15 20:39
 * @Version 1.0
 */
@Controller
public class SearchController implements CommunityConstant {
    @Autowired
    private ElasticsearchService elasticsearchService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    //search?keyword=xxx
    @RequestMapping(path="/search",method = RequestMethod.GET)
    public String search(String keyword, Page page, Model model) throws IOException {
        //搜索帖子
        List<DiscussPost> posts = elasticsearchService.searchDiscussPost(keyword, page.getCurrent()-1, page.getLimit());
        //聚合数据
        List<Map<String,Object>> discussPosts=new ArrayList<>();
        if(posts!=null){
            for (DiscussPost post : posts) {
                Map<String,Object> map=new HashMap<>();
                map.put("post",post);
                map.put("user",userService.findUserById(post.getUserId()));
                map.put("likeCount",likeService.findEntityLikeCount(ENTITY_TYPE_POST,post.getId()));

                discussPosts.add(map);
            }
        }
        model.addAttribute("discussposts",discussPosts);
        model.addAttribute("keyword",keyword);

        //分页信息
        page.setPath("/search?keyword="+keyword);
        page.setRows(elasticsearchService.getDiscussPostCount(keyword));

        return "/site/search";
    }
}
