package com.cooper.cooperposts2022.posts.exception.advice;

import com.cooper.cooperposts2022.common.ApiResult;
import com.cooper.cooperposts2022.posts.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostExceptionAdvice {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ApiResult<Void>> handlePostNotFoundException(PostNotFoundException postNotFoundException) {
        ApiResult<Void> apiResult = ApiResult.fail(HttpStatus.BAD_REQUEST, postNotFoundException.getMessage());
        return ResponseEntity.ok().body(apiResult);
    }

}
