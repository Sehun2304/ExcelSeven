package com.excelseven.backoffice;

import com.excelseven.backoffice.entity.Post;
import com.excelseven.backoffice.entity.Reply;
import com.excelseven.backoffice.entity.User;
import com.excelseven.backoffice.repository.PostRepository;
import com.excelseven.backoffice.repository.ReplyRepository;
import com.excelseven.backoffice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class SaveTest {

    UserRepository userRepository;

    PostRepository postRepository;

    ReplyRepository replyRepository;
    @Autowired
    public SaveTest(UserRepository userRepository, PostRepository postRepository, ReplyRepository replyRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.replyRepository = replyRepository;
    }

    @Test
    @Rollback(value = false)
    void save() {
        User user = new User();
        user.setUsername("kim");
        user.setPassword("1234");
        user.setEmail("kim@naver.com");
        user.setIntroduce("안녕하세요 저는 입니다.");
        user.setNickName("엑셀세븐");
        userRepository.save(user);

        Post post = new Post();
        post.setTitle("개발일지");
        post.setContent("좋아요 기능 구현 중");
        post.setUser(user);
        postRepository.save(post);

        Reply reply = new Reply();
        reply.setContent("좋은 글 감사합니다.");
        reply.setUser(user);
        reply.setPost(post);
        replyRepository.save(reply);
    }
}
