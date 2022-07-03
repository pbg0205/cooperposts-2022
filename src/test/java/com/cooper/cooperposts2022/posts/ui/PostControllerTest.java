package com.cooper.cooperposts2022.posts.ui;

import com.cooper.cooperposts2022.posts.application.PostService;
import com.cooper.cooperposts2022.posts.domain.Post;
import com.cooper.cooperposts2022.posts.domain.Question;
import com.cooper.cooperposts2022.posts.dto.PostCreateRequestDto;
import com.cooper.cooperposts2022.posts.dto.PostCreateResponseDto;
import com.cooper.cooperposts2022.posts.dto.PostLookupResponseDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

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
        ResultActions resultActions = mockMvc.perform(get("/api/v1/posts/{postId}", "abc")
                .accept(MediaType.APPLICATION_JSON));

        assertAll(
                () -> resultActions.andExpect(status().isOk()),
                () -> resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON)),
                () -> resultActions.andExpect(jsonPath("$.data.title").value("제목1")),
                () -> resultActions.andExpect(jsonPath("$.data.content").value("게시글1")),
                () -> resultActions.andExpect(jsonPath("$.data.questions[0].content").value("질문1"))
        );
    }

}