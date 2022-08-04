package com.cooper.cooperposts2022.posts.dto;

import com.cooper.cooperposts2022.posts.domain.Question;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class QuestionLookupResponseDto {

    private final String id;
    private final String content;
    private final String author;

    public static QuestionLookupResponseDto fromEntity(Question question) {
        return new QuestionLookupResponseDto(
                question.getId(),
                question.getContent(),
                question.getAuthor()
        );
    }

}
