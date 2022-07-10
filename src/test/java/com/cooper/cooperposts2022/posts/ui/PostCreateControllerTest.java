package com.cooper.cooperposts2022.posts.ui;

import com.cooper.cooperposts2022.posts.application.PostCreateService;
import com.cooper.cooperposts2022.posts.dto.PostCreateRequestDto;
import com.cooper.cooperposts2022.posts.dto.PostCreateResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostCreateController.class)
@AutoConfigureMockMvc
class PostCreateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostCreateService postService;

    @Test
    @DisplayName("게시글을 생성한다")
    void create() throws Exception {
        //given
        PostCreateRequestDto postCreateRequestDto = new PostCreateRequestDto("제목1", "내용1", "cooper");
        PostCreateResponseDto postCreateResponseDto = PostCreateResponseDto.fromEntity(postCreateRequestDto.toEntity());

        given(postService.createPost(any())).willReturn(postCreateResponseDto);

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
                () -> resultActions.andExpect(jsonPath("$.data.title").value("제목1")),
                () -> resultActions.andExpect(jsonPath("$.data.content").value("내용1")),
                () -> resultActions.andExpect(jsonPath("$.data.author").value("cooper"))
        );
    }

}