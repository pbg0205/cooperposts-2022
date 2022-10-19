package com.cooper.cooperposts2022.posts.dto;

import com.cooper.cooperposts2022.posts.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCreateResponseDto {

    private final String id;
    private final String title;
    private final String content;
    private final String author;

    public static PostCreateResponseDto fromEntity(Post post) {
        return new PostCreateResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getUserId());
    }

}
