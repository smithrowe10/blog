package com.lm.blog.utils;

import com.lm.blog.dto.UserDTO;
import com.lm.blog.entity.User;

/**
 * @author ming
 * @date 2023.10.06
 * @about ThreadLocal 处理用户信息
 */
public class UserHolder {
    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();

    public static void setUser(UserDTO userDTO) {
        tl.set(userDTO);
    }

    public static UserDTO getUser() {
        return tl.get();
    }

    public static void removeUser() {
        tl.remove();
    }
}
