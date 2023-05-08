package com.cnu.real_coding_server.service.week1.practice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.error.SlangBadRequestException;
import com.cnu.real_coding_server.model.request.PostRequest;
import com.cnu.real_coding_server.model.type.Tag;
import com.cnu.real_coding_server.service.PostService;
import com.cnu.real_coding_server.service.week1.practice.service.fixture.PostFixture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@SpringBootTest
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void init() {

    }

    @DisplayName("글 저장 테스트")
//    @Transactional
    @Test
    void createPost() throws JsonProcessingException {
        PostRequest postRequest = PostFixture.getNormalPostRequest();

        Post post = postService.createPost(postRequest);
        assertAll("verify object",
                () -> assertThat(post.getTitle()).isEqualTo(postRequest.getTitle()),
                () -> assertThat(post.getContents()).isEqualTo(postRequest.getContents()),
                () -> assertThat(post.getTag()).isEqualTo(postRequest.getTag())
        );
    }

    @DisplayName("비속어 글 저장 테스트")
    @Transactional
    @Test
    void createPostWithSlang() {
        PostRequest postRequest = PostFixture.getSlangPostRequest();
        assertThrows(SlangBadRequestException.class, () -> postService.createPost(postRequest));
    }


    @DisplayName("글 업데이트 테스트")
    @Transactional
    @Test
    void updatePost() {
        // given
        PostRequest postRequest = PostFixture.getNormalPostRequest();
        Post post = postService.createPost(postRequest);

        PostRequest updatedPostRequest = PostFixture.getNormalPostRequestUpdated();
        // when
        Optional<Post> optPost = postService.updatePost(post.getId(), updatedPostRequest);

        // then
        Post updatedPost = optPost.get();
        assertAll("verify object",
                () -> assertThat(updatedPost.getTitle()).isEqualTo(updatedPostRequest.getTitle()),
                () -> assertThat(updatedPost.getContents()).isEqualTo(updatedPostRequest.getContents()),
                () -> assertThat(updatedPost.getTag()).isEqualTo(updatedPostRequest.getTag())
        );
    }
}
