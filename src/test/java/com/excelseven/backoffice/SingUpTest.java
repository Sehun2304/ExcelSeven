package com.excelseven.backoffice;

import com.excelseven.backoffice.entity.Post;
import com.excelseven.backoffice.entity.Reply;
import com.excelseven.backoffice.entity.User;
import com.excelseven.backoffice.entity.UserRoleEnum;
import com.excelseven.backoffice.repository.PostRepository;
import com.excelseven.backoffice.repository.ReplyRepository;
import com.excelseven.backoffice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class SingUpTest {

    UserRepository userRepository;

    PostRepository postRepository;

    ReplyRepository replyRepository;

    PasswordEncoder passwordEncoder;
    @Autowired
    public SingUpTest(UserRepository userRepository, PostRepository postRepository, ReplyRepository replyRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.replyRepository = replyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 관리자용 아이디 테스트
    @Test
    @Rollback(value = false)
    void save() {
        User user = new User();
        user.setUsername("admin77777");
        String password = passwordEncoder.encode("admin77777");
        user.setPassword(password);
        user.setEmail("adimin77777@naver.com");
        user.setIntroduce("관리자용 아이디입니다.");
        user.setRole(UserRoleEnum.ADMIN);
        userRepository.save(user);

        Post post = new Post();
        post.setTitle("오늘의 공지사항");
        post.setContent("게시글 규칙을 잘 지켜주세요.");
        post.setUser(user);
        postRepository.save(post);

        Reply reply = new Reply();
        reply.setContent("좋은 글 감사합니다.");
        reply.setUser(user);
        reply.setPost(post);
        replyRepository.save(reply);
    }
}
