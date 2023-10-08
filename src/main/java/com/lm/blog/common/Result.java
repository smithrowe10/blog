package com.lm.blog.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ming
 * @date 2023.10.04
 * @about
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    private String message;

    private int code;

    private Object data;

    public static Result success() {
        return new Result("操作成功", 200, null);
    }

    public static Result success(Object data) {
        return new Result("操作成功", 200, data);
    }

    public static Result success(String message, Object data) {
        return new Result(message, 200, data);
    }

    public static Result fail(String message) {
        return new Result(message, 400, null);
    }

    public static Result fail(String message, Object data) {
        return new Result(message, 400, data);
    }
}
