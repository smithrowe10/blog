package com.lm.blog.dto;

import lombok.Data;

/**
 * @author ming
 * @date 2023.10.04
 * @about
 */

@Data
public class LoginDTO {
    private String phone;
    private String ValidateCode;
    private String password;
}
