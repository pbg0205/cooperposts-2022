package com.cooper.cooperposts2022.posts.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionTest {

    @Test
    @DisplayName("질문은 정상적으로 생성한다")
    void create() {
        assertThat(Question.create("질문1", "cooper")).isInstanceOf(Question.class);
    }

}