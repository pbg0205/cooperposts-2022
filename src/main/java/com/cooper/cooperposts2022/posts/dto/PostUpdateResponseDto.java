package com.cooper.cooperposts2022.posts.dto;

import com.cooper.cooperposts2022.posts.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PostUpdateResponseDto {

    private final String id;
    private final String title;
    private final String content;
    private final String author;

    public static PostUpdateResponseDto toEntity(Post post) {
        return new PostUpdateResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor());
    }

}
