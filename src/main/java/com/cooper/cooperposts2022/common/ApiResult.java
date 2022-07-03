package com.cooper.cooperposts2022.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ApiResult<T> {

    private int httpStatus;
    private T data;
    private String message;

    @SuppressWarnings("unchecked")
    public static <T> ApiResult<T> success(T data, HttpStatus httpStatus) {
        return (ApiResult<T>) ApiResult.builder()
                .data(data)
                .httpStatus(httpStatus.value())
                .build();
    }

    @SuppressWarnings("unchecked")
    public static <T> ApiResult<T> fail(HttpStatus httpStatus, String message) {
        return (ApiResult<T>) ApiResult.builder()
                .httpStatus(httpStatus.value())
                .message(message)
                .build();
    }

}
