package com.cooper.cooperposts2022.posts.ui;

import com.cooper.cooperposts2022.posts.dto.PostCreateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("게시글을 생성한다")
    void create() throws Exception {
        //given
        PostCreateRequestDto postCreateRequestDto = new PostCreateRequestDto("제목1", "내용1", "cooper");
        String body = objectMapper.writeValueAsString(postCreateRequestDto);


        //when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/posts")
                .content(body)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        //then
        assertAll(
                () -> resultActions.andExpect(status().isOk()),
                () -> resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON)),
                () -> resultActions.andExpect(jsonPath("$.title").value("제목1")),
                () -> resultActions.andExpect(jsonPath("$.content").value("내용1")),
                () -> resultActions.andExpect(jsonPath("$.author").value("cooper"))
        );
    }

}