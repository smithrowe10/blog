package com.lm.blog.service;

import com.lm.blog.common.Result;
import com.lm.blog.dto.BlogDTO;
import com.lm.blog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BlogService extends IService<Blog> {

    Result saveBlog(BlogDTO blogDTO);
}
