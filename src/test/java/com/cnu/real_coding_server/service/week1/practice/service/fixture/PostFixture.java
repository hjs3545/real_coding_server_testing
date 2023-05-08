package com.cnu.real_coding_server.service.week1.practice.service.fixture;

import com.cnu.real_coding_server.model.request.PostRequest;
import com.cnu.real_coding_server.model.type.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostFixture {

    static ObjectMapper mapper = new ObjectMapper();

    public static PostRequest getSlangPostRequest() {
        String title = "비속어1";
        String content = "비속어2";
        Tag tag = Tag.SPRINGBOOT;

        try {
            return mapper.readValue(
                    """
                            {
                                "title": "%s",
                                "contents": "%s",
                                "tag": "%s"
                            }
                            """.formatted(title, content, tag)
                    , PostRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static PostRequest getNormalPostRequest() {
        String title = "정상 제목";
        String content = "정상 본문";
        Tag tag = Tag.SPRINGBOOT;

        try {
            return mapper.readValue(
                    """
                            {
                                "title": "%s",
                                "contents": "%s",
                                "tag": "%s"
                            }
                            """.formatted(title, content, tag)
                    , PostRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static PostRequest getNormalPostRequestUpdated() {
        String title = "정상 제목2";
        String content = "정상 본문2";
        Tag tag = Tag.JAVA;

        try {
            return mapper.readValue(
                    """
                            {
                                "title": "%s",
                                "contents": "%s",
                                "tag": "%s"
                            }
                            """.formatted(title, content, tag)
                    , PostRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
