package com.lm.blog.controller;


import cn.hutool.core.util.RandomUtil;
import com.lm.blog.common.Result;
import com.lm.blog.dto.LoginDTO;
import com.lm.blog.dto.SendCodeDto;
import com.lm.blog.dto.UserDTO;
import com.lm.blog.service.UserService;
import com.lm.blog.utils.RegexUtils;
import com.lm.blog.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ming
 * @date 2023.10.04
 * @about
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Object get(@PathVariable Long id) {
        return userService.getById(id);
    }

    // 登录
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        return userService.login(loginDTO, session);
    }

    // 发送验证码
    @PostMapping("/sendCode")
    public Result sendCode(@RequestBody SendCodeDto sendCodeDto) {
        return userService.sendCode(sendCodeDto.getPhone());
    }

    // 用户信息
    @GetMapping("/info")
    public Result info() {
        UserDTO user = UserHolder.getUser();
        if (user == null) {
            return Result.fail("请先登录");
        }
        return Result.success(user);
    }
}
