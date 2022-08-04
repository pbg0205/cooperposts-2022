package com.cooper.cooperposts2022.posts.dto;

import com.cooper.cooperposts2022.posts.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@RequiredArgsConstructor
public class PostCreateRequestDto {

    @NotNull
    private final String title;

    @NotNull
    private final String content;

    @NotNull
    private final String author;

    public Post toEntity() {
        return Post.create(this.title, this.content, this.author);
    }

}
