package com.excelseven.backoffice.controller;

import com.excelseven.backoffice.dto.*;
import com.excelseven.backoffice.entity.UserRoleEnum;
import com.excelseven.backoffice.security.UserDetailsImpl;
import com.excelseven.backoffice.service.AdminService;
import com.excelseven.backoffice.service.PostService;
import com.excelseven.backoffice.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Secured(UserRoleEnum.Authority.ADMIN)
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    // 모든 게시글 조회
    @GetMapping("/post")
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = adminService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 게시글 조회
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long postId) {
        PostResponseDto post = adminService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    // 게시글 수정
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto updatedPost = adminService.updatePost(postId, postRequestDto, userDetails.getUser());
        return ResponseEntity.ok(updatedPost);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponseDto> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        adminService.deletePost(postId, userDetails.getUser());
        return ResponseEntity.ok(new ApiResponseDto("게시글 삭제 성공", HttpStatus.OK.value()));
    }



    @PostMapping("/reply")   //댓글 작성
    public ReplyResponseDto createReply(@RequestBody ReplyRequestDto replyRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return adminService.createReply(replyRequestDto, userDetails.getUser());
    }
    @PutMapping("/reply/{replyId}")    //댓글 수정
    public ReplyResponseDto updateReply(@PathVariable Long replyId, @RequestBody ReplyRequestDto replyRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return adminService.updateReply(replyId, replyRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/reply/{replyId}") //    삭제
    public ResponseEntity<ApiResponseDto> deleteReply(@PathVariable long replyId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            adminService.deleteReply(replyId, userDetails.getUser());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok().body(new ApiResponseDto("댓글을 찾을 수 없습니다.", HttpStatus.OK.value()));
        } finally {
        }
        return ResponseEntity.ok().body(new ApiResponseDto("댓글 삭제 성공", HttpStatus.OK.value()));
    }
}
