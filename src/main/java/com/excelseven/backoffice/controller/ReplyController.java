package com.excelseven.backoffice.controller;

import com.excelseven.backoffice.dto.ReplyRequestDto;
import com.excelseven.backoffice.entity.User;
import com.excelseven.backoffice.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/api")
public class ReplyController {
    private final ReplyService replyService;

    @ResponseBody
    @PostMapping("/post/reply/{replyId}")   //댓글 작성
    public void createReply(@RequestBody ReplyRequestDto replyRequestDto, User user){    //@AuthenticationPrincipal UserDetailsImpl userDetails
        replyService.createReply(replyRequestDto, user);//, userDetails
    }
    @ResponseBody
    @PutMapping("/post/reply/{replyId}")    //댓글 수정
    public void updateReply(@PathVariable Long replyId, @RequestBody ReplyRequestDto replyRequestDto, User user){//, @AuthenticationPrincipal UserDetailsImpl userDetails
        replyService.updateReply(replyId, replyRequestDto, user);//, userDetails
    }

    @ResponseBody
    @DeleteMapping("/post/reply/{replyId}") //    삭제
    public void deleteReply(@PathVariable long replyId, User user){ //, @AuthenticationPrincipal UserDetailsImpl userDetails
        replyService.deleteReply(replyId,user);   //, userDetails.getUser()
    }



}

