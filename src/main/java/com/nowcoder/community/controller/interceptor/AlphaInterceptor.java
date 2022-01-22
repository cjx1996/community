package com.nowcoder.community.controller.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName AlphaInterceptor
 * @Description
 * @Author cjx
 * @Date 2022/1/19 22:59
 * @Version 1.0
 */
@Component
public class AlphaInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AlphaInterceptor.class);


    //在Controller之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("preHandle: " + handler.toString());
        return true;
    }

    //在Controller之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("postHandler:" + handler.toString());
    }

    //在TemplateEngine之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.debug("afterCompletion:" + handler.toString());
    }
}
