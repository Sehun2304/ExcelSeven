package com.excelseven.backoffice.service;

import com.excelseven.backoffice.dto.ReplyRequestDto;
import com.excelseven.backoffice.dto.ReplyResponseDto;
import com.excelseven.backoffice.entity.Post;
import com.excelseven.backoffice.entity.Reply;
import com.excelseven.backoffice.entity.User;
import com.excelseven.backoffice.repository.PostRepository;
import com.excelseven.backoffice.repository.ReplyRepository;
//import com.excelseven.backoffice.security.UserDetailsImpl;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PostService postService;

    public ReplyService(ReplyRepository replyRepository, PostService postService) {
        this.replyRepository = replyRepository;
        this.postService = postService;
    }

     public ReplyResponseDto createReply(ReplyRequestDto requestDto, User user) {//댓글 생성
        Post post = postService.findPost(requestDto.getPostId());   //PostId로 게시글 찾음
        Reply reply = new Reply(requestDto.getContent(), user, post);  //댓글내용,작성자,작성글 담음

        replyRepository.save(reply);
        return new ReplyResponseDto(reply);
    }
    @Transactional
    public ReplyResponseDto updateReply(Long replyId, ReplyRequestDto requestDto, User user) {
        Reply reply = replyRepository.findById(replyId).orElseThrow();
        // 요청자가 운영자 이거나 댓글 작성자(post.user) 와 요청자(user) 가 같은지 체크
//        if (!user.getRole().equals(UserRoleEnum.ADMIN) && !reply.getUser().equals(user)) {
//            throw new RejectedExecutionException();
//        }
        reply.setContent(requestDto.getContent());//동일하면 수정댓글을 reply에 넣어줌

        return new ReplyResponseDto(reply);
    }
    public void deleteReply(Long id, User user) {
        Reply reply = replyRepository.findById(id).orElseThrow();

        // 요청자가 운영자 이거나 댓글 작성자(post.user) 와 요청자(user) 가 같은지 체크
//        if (!user.getRole().equals(UserRoleEnum.ADMIN) && !reply.getUser().equals(user)) {
//            throw new RejectedExecutionException();
//        }
        replyRepository.delete(reply);
    }
}
