package com.excelseven.backoffice.service;

import com.excelseven.backoffice.dto.ReplyRequestDto;
import com.excelseven.backoffice.dto.ReplyResponseDto;
import com.excelseven.backoffice.entity.Post;
import com.excelseven.backoffice.entity.Reply;
import com.excelseven.backoffice.entity.User;
import com.excelseven.backoffice.repository.PostRepository;
import com.excelseven.backoffice.repository.ReplyRepository;
//import com.excelseven.backoffice.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.RejectedExecutionException;

@Service
@AllArgsConstructor

public class ReplyService {
    private final ReplyRepository replyRepository;
    //    private final PostService postService;
    private final PostRepository postRepository;

    public ReplyResponseDto createReply(ReplyRequestDto requestDto, User user) {//댓글 생성
        Post post = postRepository.findById(requestDto.getPostId()).get();   //PostId로 게시글 찾음
        Reply reply = new Reply(requestDto.getContent(), user, post);  //댓글내용,작성자,작성글 담음

        replyRepository.save(reply);
        return new ReplyResponseDto(reply);
    }

    public ReplyResponseDto updateReply(Long replyId, ReplyRequestDto requestDto, User user) {
        Reply reply = replyRepository.findById(replyId).orElseThrow();
//        !user.getRole().equals(UserRoleEnum.ADMIN) (요청자가 운영자인지 체크)
        if (!reply.getUser().getId().equals(user.getId())) {  //작성자와 같은지 체크
            throw new RejectedExecutionException("작성자만 수정 가능합니다");
        }
        reply.setContent(requestDto.getContent());//동일하면 수정댓글을 reply에 넣어줌
        replyRepository.save(reply);
        return new ReplyResponseDto(reply);
    }
    public void deleteReply(Long id, User user) {
        Reply reply = replyRepository.findById(id).orElseThrow();
        if (!reply.getUser().equals(user)) {  //작성자와 같은지 체크
            throw new RejectedExecutionException("작성자만 수정 가능합니다");
        }
        replyRepository.delete(reply);
    }
}