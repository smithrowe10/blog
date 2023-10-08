package com.lm.blog.config;

import com.lm.blog.interceptor.LoginInterceptor;
import com.lm.blog.interceptor.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author ming
 * @date 2023.10.06
 * @about
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/",
                        "/user/sendCode",
                        "/user/login",
                        "/user/info"
                ).order(1);

        // token刷新的拦截器
        // 第一个拦截器：一切请求都放行，只是为了刷新Token有效期
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate))
                .addPathPatterns("/**").order(0); // 先执行
    }
}
