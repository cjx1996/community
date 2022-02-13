package com.nowcoder.community;

import com.nowcoder.community.service.AlphaService;
import com.nowcoder.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName TransactionTests
 * @Description
 * @Author cjx
 * @Date 2022/2/13 11:42
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SensitiveFilter.class)
public class TransactionTests {
    @Autowired
    private AlphaService alphaService;

    @Test
    public void test() {
        Object obj = alphaService.save1();
        System.out.println(obj);
    }
    @Test
    public void test2() {
        Object obj = alphaService.save2();
        System.out.println(obj);
    }
}
