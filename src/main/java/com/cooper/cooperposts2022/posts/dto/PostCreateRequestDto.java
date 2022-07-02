package com.cooper.cooperposts2022.posts.dto;

import com.cooper.cooperposts2022.posts.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostCreateRequestDto {

    private final String title;
    private final String content;
    private final String author;

    public Post toEntity() {
        return Post.create(this.title, this.content, this.author);
    }

}
