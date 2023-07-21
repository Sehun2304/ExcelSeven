package com.excelseven.backoffice.controller;

import com.excelseven.backoffice.dto.ReplyRequestDto;
import com.excelseven.backoffice.dto.ReplyResponseDto;
import com.excelseven.backoffice.entity.User;
import com.excelseven.backoffice.repository.UserRepository;
import com.excelseven.backoffice.security.UserDetailsImpl;
import com.excelseven.backoffice.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RestController
@AllArgsConstructor
@RequestMapping("/api/post/reply")
public class ReplyController {

    private final ReplyService replyService;
    private final UserRepository userRepository;

    @PostMapping("")   //댓글 작성
    public ReplyResponseDto createReply(@RequestBody ReplyRequestDto replyRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return replyService.createReply(replyRequestDto, userDetails.getUser());
    }
    @PutMapping("/{replyId}")    //댓글 수정
    public ReplyResponseDto updateReply(@PathVariable Long replyId, @RequestBody ReplyRequestDto replyRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return replyService.updateReply(replyId, replyRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/{replyId}") //    삭제
    public void deleteReply(@PathVariable long replyId, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        User user = userRepository.findById(1L).get();
        replyService.deleteReply(replyId, userDetails.getUser());
    }



}

