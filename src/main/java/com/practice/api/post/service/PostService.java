package com.practice.api.post.service;

import com.practice.api.post.dto.PostDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    List<PostDto> getPostList();

    PostDto createPost(PostDto post);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto post);

    void deletePost(Long id);
}
