package com.ybkim.springstudy.springboot.service;

import com.ybkim.springstudy.springboot.domain.posts.Posts;
import com.ybkim.springstudy.springboot.domain.posts.PostsRepository;
import com.ybkim.springstudy.springboot.web.dto.PostsListResponseDto;
import com.ybkim.springstudy.springboot.web.dto.PostsReponseDto;
import com.ybkim.springstudy.springboot.web.dto.PostsSaveRequestDto;
import com.ybkim.springstudy.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public  void delete (Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts); //JpaRepository에서 이미 delete를 지원하고 있어 이를 활용한다.
    }

    public PostsReponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return  new PostsReponseDto(entity);
    }
}
