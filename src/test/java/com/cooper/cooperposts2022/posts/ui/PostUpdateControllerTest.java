package com.cooper.cooperposts2022.posts.ui;

import com.cooper.cooperposts2022.posts.application.PostUpdateService;
import com.cooper.cooperposts2022.posts.domain.Post;
import com.cooper.cooperposts2022.posts.dto.PostUpdateRequestDto;
import com.cooper.cooperposts2022.posts.dto.PostUpdateResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PostUpdateController.class)
class PostUpdateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostUpdateService postUpdateService;

    @Test
    @DisplayName("기존 게시글이 존재하면 게시글을 변경한다")
    void update() throws Exception {
        //given
        String modifiedTitle = "modifiedTitle";
        String modifiedContent = "modifiedContent";
        String modifiedAuthor = "modifiedAuthor";
        String postId = "postId";

        PostUpdateRequestDto postUpdateRequestDto = new PostUpdateRequestDto(modifiedTitle, modifiedContent, modifiedAuthor);
        Post post = Post.create(modifiedTitle, modifiedContent, modifiedAuthor);

        PostUpdateResponseDto postUpdateResponseDto = PostUpdateResponseDto.toEntity(post);
        given(postUpdateService.updatePost(anyString(), any(PostUpdateRequestDto.class))).willReturn(postUpdateResponseDto);

        String requestBody = objectMapper.writeValueAsString(postUpdateRequestDto);

        //when
        ResultActions result = mockMvc.perform(put("/api/v1/posts/{postId}", postId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        result.andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$.data.title").value(modifiedTitle),
                jsonPath("$.data.content").value(modifiedContent),
                jsonPath("$.data.author").value(modifiedAuthor)
        );
    }

}