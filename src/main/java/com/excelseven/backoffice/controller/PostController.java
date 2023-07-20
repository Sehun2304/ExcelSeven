package com.excelseven.backoffice.controller;

import com.excelseven.backoffice.dto.PostRequestDto;
import com.excelseven.backoffice.dto.PostResponseDto;
import com.excelseven.backoffice.entity.Post;
import com.excelseven.backoffice.security.UserDetailsImpl;
import com.excelseven.backoffice.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/posts")
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/posts/{postId}")
    public PostResponseDto getPostById(@PathVariable Long postId) {
        log.info("id={}", postId);
        return postService.getPostById(postId);
    }

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(postRequestDto);
    }

    @PutMapping("/posts/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(postId, postRequestDto);
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
