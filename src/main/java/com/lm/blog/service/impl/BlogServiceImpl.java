package com.lm.blog.service.impl;

import com.lm.blog.common.Result;
import com.lm.blog.dto.BlogDTO;
import com.lm.blog.dto.UserDTO;
import com.lm.blog.entity.Blog;
import com.lm.blog.mapper.BlogMapper;
import com.lm.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.blog.utils.UserHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    /**
     * 添加博客
     *
     * @param blogDTO
     * @return
     */
    @Override
    public Result saveBlog(BlogDTO blogDTO) {
        UserDTO userDTO = UserHolder.getUser();
        if (userDTO == null) {
            return Result.fail("请先登录");
        }
        Long userId = userDTO.getId();
        Blog blog = new Blog();
        blog.setUserId(userId);
        blog.setTitle(blogDTO.getTitle());
        blog.setDescription(blogDTO.getDescription());
        blog.setContent(blogDTO.getContent());
        blog.setCreated(LocalDateTime.now());
        save(blog);

        return Result.success();
    }
}
