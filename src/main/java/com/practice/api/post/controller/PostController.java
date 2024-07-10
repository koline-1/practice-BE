package com.practice.api.post.controller;

import com.practice.api.common.BaseResponse;
import com.practice.api.post.dto.PostDto;
import com.practice.api.post.service.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse> getPostList() {
        return ResponseEntity.ok(new BaseResponse(true, "", postService.getPostList()));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createPost(
            @RequestBody PostDto post
    ) {
        if (post.getTitle() == null || post.getTitle().isBlank()) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "제목은 필수입력 항목입니다.", null));
        }
        
        if (post.getContent() == null || post.getContent().isBlank()) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "내용은 필수입력 항목입니다.", null));
        }

        return ResponseEntity.ok(new BaseResponse(true, "", postService.createPost(post)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getPostById(
            @PathVariable(name = "id") String id
    ) {
        PostDto post = postService.getPostById(Long.valueOf(id));

        if (post == null) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "유효하지 않은 id입니다.", null));
        }

        return ResponseEntity.ok(new BaseResponse(true, "", post));
    }

    @PatchMapping
    public ResponseEntity<BaseResponse> updatePost(
            @RequestBody PostDto post
    ) {
        if (post.getId() == null || post.getId() < 0) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "유효하지 않은 id입니다.", null));
        }

        if (post.getTitle() == null || post.getTitle().isBlank()) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "제목은 필수입력 항목입니다.", null));
        }

        if (post.getContent() == null || post.getContent().isBlank()) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "내용은 필수입력 항목입니다.", null));
        }

        PostDto target = postService.getPostById(post.getId());

        if (target == null) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "유효하지 않은 게시물입니다.", null));
        }

        PostDto updatedPost = postService.updatePost(post);

        return ResponseEntity.ok(new BaseResponse(true, "", updatedPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deletePost(
            @PathVariable(name = "id") String id
    ) {
        PostDto post = postService.getPostById(Long.valueOf(id));

        if (post == null) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "유효하지 않은 id입니다.", null));
        }

        postService.deletePost(Long.valueOf(id));

        Map<String, String> result = Map.of("deletedPost", id);

        return ResponseEntity.ok(new BaseResponse(true, "", result));
    }
}