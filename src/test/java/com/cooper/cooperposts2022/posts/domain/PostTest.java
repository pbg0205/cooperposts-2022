package com.cooper.cooperposts2022.posts.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {

    @Test
    @DisplayName("게시글을 정상적으로 생성한다")
    void create() {
        assertThat(Post.create("제목1", "게시글1", "cooper")).isInstanceOf(Post.class);
    }

}