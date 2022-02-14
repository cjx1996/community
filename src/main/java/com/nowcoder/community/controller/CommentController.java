package com.nowcoder.community.controller;

import com.nowcoder.community.entity.Comment;
import com.nowcoder.community.service.CommentService;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * @ClassName CommentController
 * @Description
 * @Author cjx
 * @Date 2022/2/14 0:20
 * @Version 1.0
 */
@Controller
@RequestMapping(path="/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path="/add/{discussPostId}",method= RequestMethod.POST)
    public String addComment(@PathVariable("discussPostId")int discussPostId, Comment comment){
            comment.setUserId(hostHolder.getUser().getId());
            comment.setStatus(0);
            comment.setCreateTime(new Date());
        System.out.println(comment);
            commentService.addComment(comment);
            return "redirect:/discuss/detail/"+discussPostId;
    }


}