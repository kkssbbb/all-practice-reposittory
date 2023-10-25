package com.firstwebservice.firstwebservice.service;

import com.firstwebservice.firstwebservice.domain.posts.Posts;
import com.firstwebservice.firstwebservice.domain.posts.PostsRepository;

import com.firstwebservice.firstwebservice.web.dto.PostsListResponseDto;
import com.firstwebservice.firstwebservice.web.dto.PostsResponseDto;
import com.firstwebservice.firstwebservice.web.dto.PostsSaveRequestDto;
import com.firstwebservice.firstwebservice.web.dto.PostsUpdateRequestDto;


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
    public Long save(PostsSaveRequestDto requestDto) {

        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));


        return new PostsResponseDto(entity);

        }

    @Transactional(readOnly = true) //(readOnly = true)을 주면 트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회 속도 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("해당 게시글이 없습니다. id= "+id));

        postsRepository.delete(posts);
    }
}
