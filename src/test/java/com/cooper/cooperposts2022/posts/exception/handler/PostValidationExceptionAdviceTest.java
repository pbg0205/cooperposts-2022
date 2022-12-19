package com.cooper.cooperposts2022.posts.exception.handler;


import com.cooper.cooperposts2022.posts.application.PostCreateService;
import com.cooper.cooperposts2022.posts.application.PostUpdateService;
import com.cooper.cooperposts2022.posts.dto.PostCreateRequestDto;
import com.cooper.cooperposts2022.posts.dto.PostUpdateRequestDto;
import com.cooper.cooperposts2022.posts.controller.PostCreateController;
import com.cooper.cooperposts2022.posts.controller.PostUpdateController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {PostCreateController.class, PostUpdateController.class})
@AutoConfigureMockMvc
@Disabled
class PostValidationExceptionAdviceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostCreateService postCreateService;

    @MockBean
    private PostUpdateService postUpdateService;

    @Test
    @DisplayName("게시글 생성 요청 바디 검증을 통과하지 못하면 예외를 반환한다")
    void isInvalidRequestBodyWhenPostCreate() throws Exception {
        //given
        PostCreateRequestDto postCreateRequestDto = new PostCreateRequestDto(null, null, null);
        String requestBody = objectMapper.writeValueAsString(postCreateRequestDto);

        //when
        mockMvc.perform(post("/api/v1/posts")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("게시글 수정 요청 바디 검증을 통과하지 못하면 예외를 반환한다")
    void isInvalidRequestBodyWhenPostUpdate() throws Exception {
        //given
        PostUpdateRequestDto postUpdateRequestDto = new PostUpdateRequestDto(null, null, null);
        String requestBody = objectMapper.writeValueAsString(postUpdateRequestDto);

        //when
        mockMvc.perform(put("/api/v1/posts/{postId}", "id01")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
