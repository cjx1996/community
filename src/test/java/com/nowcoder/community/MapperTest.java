package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.SuppressSignatureCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MapperTest
 * @Description
 * @Author cjx
 * @Date 2022/1/9 21:56
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {
    @Autowired
    private LoginTicketMapper loginTicketMapper;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser() {
        User user = userMapper.selectById(101);
        System.out.println(user);
        User user1 = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user1);
        User user2 = userMapper.selectByName("liubei");
        System.out.println(user2);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("23456");
        user.setSalt("abs");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/999.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser() {
        int rows = userMapper.updateHeader(150, "http://nowcoder.com/998.png");
        System.out.println(rows);
        int rows1 = userMapper.updateStatus(150, 2);
        System.out.println(rows1);
        int rows2 = userMapper.updatePassword(150, "123456");
        System.out.println(rows2);
    }

    @Test
    public void testSelectPosts() {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149, 0, 10);
        for (DiscussPost post : list) {
            System.out.println(post);
        }
        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }

    @Test
    public void testInsertLoginTest() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));
        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
        loginTicketMapper.updateStatus(loginTicket.getTicket(), 1);
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }


}
