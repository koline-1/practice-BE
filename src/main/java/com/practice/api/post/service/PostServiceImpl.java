package com.practice.api.post.service;

import com.practice.api.post.dto.PostDto;
import com.practice.api.post.entity.PostEntity;
import com.practice.api.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostDto> getPostList() {
        return postRepository.findAll().stream().map(PostEntity::toDto).toList();
    }

    @Override
    public PostDto createPost(PostDto post) {
        return postRepository.save(post.toEntity()).toDto();
    }

    @Override
    public PostDto getPostById(Long id) {
        return postRepository.findById(id).map(PostEntity::toDto).orElse(null);
    }

    @Override
    public PostDto updatePost(PostDto post) {
        return postRepository.save(post.toEntity()).toDto();
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
