package com.cnu.real_coding_server.service.week1.practice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.cnu.real_coding_server.service.valid.PostValidService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class PostValidServiceTest {

    @Autowired
    PostValidService postValidService;


    @DisplayName("post 제목/본문에 비속어가 있나 테스트")
    @Test
    void testValidPostIncludeSlang() {
        // given 시나리오
        String testTitle = "비속어가 섞인 제목";
        String testContent = "비속어가 섞인 욕";
        List<String> slangList = List.of("비속어", "비속어2");

        boolean validPost = postValidService.isSlangInclude(slangList, testTitle, testContent);
        // then 검증
        assertThat(validPost).isEqualTo(true);
    }

    @DisplayName("post 제목에 비속어가 있나 테스트")
    @Test
    void testValidPostTitleIncludeSlang() {
        // given 시나리오
        String testSlangTitle = "비속어가 섞인 제목";
        String testNoneSlangTitle = "비1속어가 없는 제목";
        String testContent = "비1속어가 없는 내용";
        List<String> slangList = List.of("비속어", "비속어2");

        boolean validSlangPostTitle = postValidService.isSlangInclude(slangList, testSlangTitle, testContent);
        boolean validNoneSlangPostTitle = postValidService.isSlangInclude(slangList, testNoneSlangTitle, testContent);


        // then 검증
        assertAll("verify object",
                () -> assertThat(validSlangPostTitle).isEqualTo(true),
                () -> assertThat(validNoneSlangPostTitle).isEqualTo(false)
        );
    }
    @DisplayName("post 내용에 비속어가 있나 테스트")
    @Test
    void testValidPostContentIncludeSlang() {
        // given 시나리오
        String testNoneSlangTitle = "비1속어가 없는 제목";
        String testSlangContent = "비속어가 있는 내용";
        String testNoneSlangContent = "비1속어가 없는 내용";
        List<String> slangList = List.of("비속어", "비속어2");

        boolean validSlangPostContent = postValidService.isSlangInclude(slangList, testNoneSlangTitle, testSlangContent);
        boolean validNoneSlangPostContent = postValidService.isSlangInclude(slangList, testNoneSlangTitle, testNoneSlangContent);


        // then 검증
        assertAll("verify object",
                () -> assertThat(validSlangPostContent).isEqualTo(true),
                () -> assertThat(validNoneSlangPostContent).isEqualTo(false)
        );
    }
}
