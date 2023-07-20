package com.excelseven.backoffice.service;

import com.excelseven.backoffice.dto.PostRequestDto;
import com.excelseven.backoffice.dto.PostResponseDto;
import com.excelseven.backoffice.entity.Post;
import com.excelseven.backoffice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public PostResponseDto getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return mapToResponseDto(post);
    }

    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = new Post();
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        Post savedPost = postRepository.save(post);
        return mapToResponseDto(savedPost);
    }

    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        existingPost.setTitle(postRequestDto.getTitle());
        existingPost.setContent(postRequestDto.getContent());
        Post updatedPost = postRepository.save(existingPost);
        return mapToResponseDto(updatedPost);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    private PostResponseDto mapToResponseDto(Post post) {
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setBoardId(post.getId());
        // 나머지 필드 설정
        return postResponseDto;
    }
}

