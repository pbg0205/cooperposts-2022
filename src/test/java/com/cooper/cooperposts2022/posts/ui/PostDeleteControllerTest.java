package com.cooper.cooperposts2022.posts.ui;

import com.cooper.cooperposts2022.posts.application.PostDeleteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(PostDeleteController.class)
class PostDeleteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostDeleteService postDeleteService;

    @Test
    @DisplayName("게시글을 삭제한다")
    void remove() throws Exception {
        //given
        willDoNothing().given(postDeleteService).deletePost(any());

        //when
        ResultActions resultActions = mockMvc.perform(delete("/api/v1/posts/{postId}", "postId")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON)
        );
    }
}