package com.cnu.real_coding_server.service.week1.base.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.model.type.Tag;
import com.cnu.real_coding_server.repository.PostRepository;
import com.cnu.real_coding_server.service.PostService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PostServiceWithSpringWithMockTest {

    @Autowired
    PostService postService;

    @MockBean
    PostRepository postRepository;

    @BeforeEach
    void init() {

    }

    @DisplayName("테스트 코드에서 Spring Component 실행해보기")
    @Test
    void testDoCodeRun() {
        // given : 로직 수행을 위한 상황(데이터) 제시
        String title = "만나서";
        String contents = "반가워요";
        Tag tag = Tag.SPRINGBOOT;

        Post testPostData = Post.builder()
                .title(title)
                .contents(contents)
                .tag(tag)
                .build();
        when(postService.getPost(1)).thenReturn(Optional.of(testPostData)); // when 때 실행 시 줄 데이터 세팅

        // when : 로직을 수행
        Optional<Post> optPost = postService.getPost(1);
        // then : 생각한 대로 결과가 나오는지 확인
        assertThat(optPost.isPresent()).isTrue();
    }
}
