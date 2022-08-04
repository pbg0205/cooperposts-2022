package com.cooper.cooperposts2022.posts.exception.handler;

import com.cooper.cooperposts2022.common.ApiResult;
import com.cooper.cooperposts2022.logging.DebugRequired;
import com.cooper.cooperposts2022.posts.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostNotFoundExceptionAdvice {

    @DebugRequired
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ApiResult<String>> handlePostNotFoundException(PostNotFoundException postNotFoundException) {
        ApiResult<String> apiResult = ApiResult.fail(HttpStatus.BAD_REQUEST, postNotFoundException.getMessage());
        return ResponseEntity.ok().body(apiResult);
    }

}
