package com.lm.blog.controller;


import com.lm.blog.common.Result;
import com.lm.blog.dto.BlogDTO;
import com.lm.blog.dto.UserDTO;
import com.lm.blog.entity.Blog;
import com.lm.blog.service.BlogService;
import com.lm.blog.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/add")
    public Result add(@RequestBody BlogDTO blogDTO) {
        return blogService.saveBlog(blogDTO);
    }
}
