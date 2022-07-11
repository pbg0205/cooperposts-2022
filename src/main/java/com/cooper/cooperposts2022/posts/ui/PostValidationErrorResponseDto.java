package com.cooper.cooperposts2022.posts.ui;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;

@Getter
public class PostValidationErrorResponseDto {

    private final Map<String, String> errors;

    public PostValidationErrorResponseDto(Map<String, String> errors) {
        this.errors = Collections.unmodifiableMap(errors);
    }

}
