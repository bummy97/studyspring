package com.ybkim.springstudy.springboot.service;

import com.ybkim.springstudy.springboot.domain.posts.Posts;
import com.ybkim.springstudy.springboot.domain.posts.PostsRepository;
import com.ybkim.springstudy.springboot.web.dto.PostsReponseDto;
import com.ybkim.springstudy.springboot.web.dto.PostsSaveRequestDto;
import com.ybkim.springstudy.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto)
    {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsReponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return  new PostsReponseDto(entity);
    }
}
