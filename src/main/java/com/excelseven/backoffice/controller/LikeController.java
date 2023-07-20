package com.excelseven.backoffice.controller;

import com.excelseven.backoffice.dto.ApiResponseDto;
import com.excelseven.backoffice.entity.User;
import com.excelseven.backoffice.repository.UserRepository;
import com.excelseven.backoffice.security.UserDetailsImpl;
import com.excelseven.backoffice.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;
    private final UserRepository userRepository;

    /**
     * 게시글 좋아요
     * @AuthenticationPrincipal 로그인 기능 구현시 적용
     */
    @PostMapping("/post/{postId}/like")
    public ResponseEntity<ApiResponseDto> likePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
        // User user = userdetil.get()
//        User user = findUser(1L);
        User user = userDetails.getUser();
        try {
            likeService.likePost(user, postId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        ApiResponseDto apiResponseDto = new ApiResponseDto("게시글 좋아요 성공", HttpStatus.ACCEPTED.value());
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    /**
     * 게시글 좋아요 삭제
     * @param postId
     * @return
     */
    @DeleteMapping("/post/{postId}/like")
    public ResponseEntity<ApiResponseDto> DeleteLikePost(@PathVariable Long postId) {
        User user = findUser(1L);

        try {
            likeService.deleteLikePost(user, postId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        ApiResponseDto apiResponseDto = new ApiResponseDto("게시글 좋아요 취소 성공", HttpStatus.ACCEPTED.value());
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    /**
     * 댓글 좋아요
     * @AuthenticationPrincipal 로그인 기능 구현시 적용
     */
    @PostMapping("/reply/{replyId}/like")
    public ResponseEntity<ApiResponseDto> likeReply(@PathVariable Long replyId) {
        User user = findUser(1L);

        try {
            likeService.likeReply(user, replyId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        ApiResponseDto apiResponseDto = new ApiResponseDto("댓글 좋아요 성공", HttpStatus.ACCEPTED.value());
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    // 댓글 좋아요 삭제 요청

    @DeleteMapping("/reply/{replyId}/like")
    public ResponseEntity<ApiResponseDto> DeleteLikeReply(@PathVariable Long replyId) {
        User user = findUser(1L);

        try {
            likeService.deleteLikeReply(user, replyId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        ApiResponseDto apiResponseDto = new ApiResponseDto("댓글 좋아요 삭제 요청", HttpStatus.ACCEPTED.value());
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));
    }
}
