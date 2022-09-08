package com.cooper.cooperposts2022.posts.controller;

import com.cooper.cooperposts2022.posts.application.PostLookupService;
import com.cooper.cooperposts2022.posts.domain.Post;
import com.cooper.cooperposts2022.posts.domain.Question;
import com.cooper.cooperposts2022.posts.dto.PostLookupResponseDto;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostLookupController.class)
@AutoConfigureMockMvc
public class PostLookupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostLookupService postService;

    @Test
    @DisplayName("게시글을 조회한다")
    void findById() throws Exception {
        //given
        Post post = Post.create("제목1", "게시글1", "cooper");
        post.addAnswer(Question.create("질문1", "park"));
        post.addAnswer(Question.create("질문2", "park2"));

        PostLookupResponseDto postLookupResponseDto = PostLookupResponseDto.fromEntity(post);

        given(postService.findById(any())).willReturn(postLookupResponseDto);

        //when
        ResultActions result = mockMvc.perform(get("/api/v1/posts/{postId}", "abc")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$.data.title").value("제목1"),
                jsonPath("$.data.content").value("게시글1"),
                jsonPath("$.data.questions[0].content").value("질문1")
        );
    }

}