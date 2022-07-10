package com.cooper.cooperposts2022.posts.domain;

import com.cooper.cooperposts2022.posts.dto.PostUpdateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PostTest {

    @Test
    @DisplayName("게시글을 정상적으로 생성한다")
    void create() {
        assertThat(Post.create("제목1", "게시글1", "cooper")).isInstanceOf(Post.class);
    }

    @Test
    @DisplayName("게시글을 정상적으로 수정한다.")
    void update() {
        //given
        PostUpdateRequestDto postUpdateRequestDto = new PostUpdateRequestDto("수정제목", "수정게시글", "park");
        Post post = Post.create("제목1", "게시글1", "cooper");

        //when
        post.updatePost(postUpdateRequestDto);

        //then
        assertAll(
                () -> assertThat(post.getAuthor()).isEqualTo("park"),
                () -> assertThat(post.getTitle()).isEqualTo("수정제목"),
                () -> assertThat(post.getContent()).isEqualTo("수정게시글")
        );

    }

}