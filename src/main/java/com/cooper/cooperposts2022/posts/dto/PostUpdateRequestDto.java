package com.cooper.cooperposts2022.posts.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostUpdateRequestDto {

    private final String title;
    private final String content;
    private final String author;

}
