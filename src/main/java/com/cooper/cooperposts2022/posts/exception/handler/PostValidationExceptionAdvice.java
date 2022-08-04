package com.cooper.cooperposts2022.posts.exception.handler;

import com.cooper.cooperposts2022.common.ApiResult;
import com.cooper.cooperposts2022.logging.DebugRequired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class PostValidationExceptionAdvice {

    @DebugRequired
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResult<Map<String, String>>> handleRequestValidationException(
            MethodArgumentNotValidException methodArgumentNotValidException
    ) {
        Map<String, String> errors = methodArgumentNotValidException.getBindingResult()
                .getAllErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(),
                        DefaultMessageSourceResolvable::getDefaultMessage)
                );

        ApiResult<Map<String, String>> apiResult = ApiResult.fail(HttpStatus.BAD_REQUEST, errors);

        return ResponseEntity.badRequest().body(apiResult);
    }

}
