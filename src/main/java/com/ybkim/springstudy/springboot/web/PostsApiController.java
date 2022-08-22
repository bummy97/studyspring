package com.ybkim.springstudy.springboot.web;

import com.ybkim.springstudy.springboot.service.PostsService;
import com.ybkim.springstudy.springboot.web.dto.PostsReponseDto;
import com.ybkim.springstudy.springboot.web.dto.PostsSaveRequestDto;
import com.ybkim.springstudy.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsSerivce;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto)
    {
        return  postsSerivce.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsSerivce.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsSerivce.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsReponseDto findById(@PathVariable Long id){
        return postsSerivce.findById(id);
    }
}
