package com.lm.blog.interceptor;

import cn.hutool.core.bean.BeanUtil;
import com.lm.blog.dto.UserDTO;
import com.lm.blog.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import static com.lm.blog.utils.Constants.LOGIN_USER_KEY;

/**
 * @author ming
 * @date 2023.10.06
 * @about
 */

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.判断是否需要拦截（ThreadLocal中是否有用户）
        if (UserHolder.getUser() == null) {
            // 没有，需要拦截，设置状态码
            response.setStatus(401);
            // 拦截
            log.info("拦截到一个请求：" + request.getServerName());
            return false;
        }
        // 有用户，则放行
        return true;
    }
}
