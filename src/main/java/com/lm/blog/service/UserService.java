package com.lm.blog.service;

import com.lm.blog.common.Result;
import com.lm.blog.dto.LoginDTO;
import com.lm.blog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserService extends IService<User> {

    Result login(LoginDTO loginDTO, HttpSession session);

    Result sendCode(String phone);
}
