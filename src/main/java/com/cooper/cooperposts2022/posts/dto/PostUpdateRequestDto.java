package com.cooper.cooperposts2022.posts.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class PostUpdateRequestDto {

    @NotNull
    private final String title;

    @NotNull
    private final String content;

    @NotNull
    private final String author;

}
