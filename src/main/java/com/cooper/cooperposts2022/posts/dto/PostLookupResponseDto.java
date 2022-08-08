package com.cooper.cooperposts2022.posts.dto;

import com.cooper.cooperposts2022.posts.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PostLookupResponseDto implements Serializable {

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
