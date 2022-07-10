package com.cooper.cooperposts2022.posts.exception.handler;

import com.cooper.cooperposts2022.posts.application.PostService;
import com.cooper.cooperposts2022.posts.exception.PostNotFoundException;
import com.cooper.cooperposts2022.posts.ui.PostController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
class PostExceptionAdviceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    @DisplayName("게시글 조회 실패 반환 메세지를 확인한다")
    void handlePostNotFoundException() throws Exception {
        //given
        String postId = "postId01";
        given(postService.findById(postId)).willThrow(new PostNotFoundException(postId));

        //when
        ResultActions result = mockMvc.perform(get("/api/v1/posts/{postId}", postId)
                .accept(MediaType.APPLICATION_JSON));

        //then
        assertAll(
                () -> result.andExpect(jsonPath("$.httpStatus").value(HttpStatus.BAD_REQUEST.value())),
                () -> result.andExpect(jsonPath("$.message")
                        .value(String.format("게시글을 찾을 수 없습니다 : (postId : %s)", postId)))
        );
    }

}