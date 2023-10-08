package com.lm.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import com.lm.blog.common.Result;
import com.lm.blog.dto.LoginDTO;
import com.lm.blog.dto.UserDTO;
import com.lm.blog.entity.User;
import com.lm.blog.mapper.UserMapper;
import com.lm.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.blog.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.lm.blog.utils.Constants.*;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @Override
    public Result sendCode(String phone) {
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.fail("手机号码格式错误");
        }

        String code = RandomUtil.randomNumbers(6);

        // 发送验证码
        log.info("验证码为: {}", code);

        stringRedisTemplate.opsForValue().set(CACHE_CODE_PREFIX + phone, code, 1, TimeUnit.MINUTES);

        return Result.success("发送成功");
    }

    /**
     * 登录
     *
     * @param loginDTO
     * @param session
     * @return
     */
    @Override
    public Result login(LoginDTO loginDTO, HttpSession session) {
        String phone = loginDTO.getPhone();
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.fail("手机号码格式错误！");
        }

        String cacheCode = stringRedisTemplate.opsForValue().get(CACHE_CODE_PREFIX + phone);
        String code = loginDTO.getValidateCode();
//        if (cacheCode == null || !cacheCode.equals(code)) {
//            // 不一致，报错
//            return Result.fail("验证码错误");
//        }

        User user = query().eq("phone", phone).one();
        if (user == null) {
            // 注册
            user = createUserWithPhone(phone);
        }

        // 保存用户信息到redis

        String token = UUID.randomUUID().toString(true);
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        // 将userDTO转变为一个Map
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));

        String tokenKey = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
        stringRedisTemplate.expire(tokenKey, LOGIN_USER_TTL, TimeUnit.MINUTES);
        return Result.success(token);
    }

    /**
     * 创建新用户
     *
     * @param phone
     * @return
     */
    private User createUserWithPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setUsername(USERNAME_PREFIX + RandomUtil.randomString(6) + phone);
        user.setStatus(0);
        user.setCreated(LocalDateTime.now());
        save(user);
        return user;
    }
}
