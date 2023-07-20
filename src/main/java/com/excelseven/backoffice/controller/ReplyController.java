package com.excelseven.backoffice.controller;

import com.excelseven.backoffice.dto.ReplyRequestDto;
import com.excelseven.backoffice.dto.ReplyResponseDto;
import com.excelseven.backoffice.entity.Reply;
import com.excelseven.backoffice.entity.User;
import com.excelseven.backoffice.repository.UserRepository;
import com.excelseven.backoffice.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ReplyController {

    private final ReplyService replyService;
    private final UserRepository userRepository;

    @PostMapping("/post/reply")   //댓글 작성
    public ReplyResponseDto createReply(@RequestBody ReplyRequestDto replyRequestDto, User user){    //@AuthenticationPrincipal UserDetailsImpl userDetails
        return replyService.createReply(replyRequestDto, user);
    }
    @PutMapping("/post/reply/{replyId}")    //댓글 수정
    public ReplyResponseDto updateReply(@PathVariable Long replyId, @RequestBody ReplyRequestDto replyRequestDto, User user){//, @AuthenticationPrincipal UserDetailsImpl userDetails
        return replyService.updateReply(replyId, replyRequestDto, user);//, userDetails
    }

    @DeleteMapping("/post/reply/{replyId}") //    삭제
    public void deleteReply(@PathVariable long replyId, User user){ //, @AuthenticationPrincipal UserDetailsImpl userDetails
        replyService.deleteReply(replyId,user);   //, userDetails.getUser()
    }



}

