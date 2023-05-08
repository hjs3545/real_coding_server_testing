package com.cnu.real_coding_server.service.week1.base.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.service.PostService;
import com.cnu.real_coding_server.service.week1.practice.service.fixture.PostFixture;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class PostServiceWithSpringTest {
    @Autowired
    PostService postService;

    @DisplayName("테스트 코드에서 Spring Component 실행해보기")
    @Test
    void testDoCodeRun() {
        Post post = postService.createPost(PostFixture.getNormalPostRequest());
        Optional<Post> optPost = postService.getPost(post.getId());
        assertThat(optPost.isPresent()).isTrue();
    }
}
