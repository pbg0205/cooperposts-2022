package com.cooper.cooperposts2022.posts.dto;

import com.cooper.cooperposts2022.posts.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PostLookupResponseDto {

    private final String id;
    private final String title;
    private final String content;
    private final String author;
    private final List<QuestionLookupResponseDto> questions;

    public static PostLookupResponseDto fromEntity(Post post) {
        return new PostLookupResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getQuestions().convertToLookupResponseDtos());
    }

}
