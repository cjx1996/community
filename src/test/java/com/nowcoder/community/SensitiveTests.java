package com.nowcoder.community;

import com.nowcoder.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName SensitiveTests
 * @Description
 * @Author cjx
 * @Date 2022/1/28 16:03
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SensitiveFilter.class)
public class SensitiveTests {
    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter() {
        String text = "abcdefgfabc";
        System.out.println(sensitiveFilter.filter(text));
    }
}
