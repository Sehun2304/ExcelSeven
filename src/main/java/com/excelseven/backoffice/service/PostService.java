package com.excelseven.backoffice.service;

import com.excelseven.backoffice.entity.Post;
import com.excelseven.backoffice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public Post createPost(Post post) {
        // 게시물 작성 시 인가(Authorization)를 검증하는 로직을 추가해야합니다.
        // JWT 토큰을 이용한 인가 검증 로직을 구현해야 합니다.
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, Post post) {
        // 게시물 수정 시 인가(Authorization)를 검증하는 로직을 추가해야합니다.
        // JWT 토큰을 이용한 인가 검증 로직을 구현해야 합니다.
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        return postRepository.save(existingPost);
    }

    public void deletePost(Long postId) {
        // 게시물 삭제 시 인가(Authorization)를 검증하는 로직을 추가해야합니다.
        // JWT 토큰을 이용한 인가 검증 로직을 구현해야 합니다.
        postRepository.deleteById(postId);
    }

    public Post findPost(Long postId) { //댓글 생성시 유효 게시글 찾기용
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
    }
}
