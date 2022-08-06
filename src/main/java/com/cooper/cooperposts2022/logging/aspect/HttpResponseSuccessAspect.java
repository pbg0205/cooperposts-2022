package com.cooper.cooperposts2022.logging.aspect;

import com.cooper.cooperposts2022.common.ApiResult;
import com.cooper.cooperposts2022.logging.template.ApiResponseSuccessTemplate;
import com.cooper.cooperposts2022.posts.dto.PostCreateResponseDto;
import com.cooper.cooperposts2022.posts.dto.PostLookupResponseDto;
import com.cooper.cooperposts2022.posts.dto.PostUpdateResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class HttpResponseSuccessAspect {

    private static final String REQUEST_ID_KEY_NAME = "requestId";

    @AfterReturning(pointcut = "com.cooper.cooperposts2022.logging.pointcuts.HttpMethodPointcuts.postPointcut()", returning = "response")
    public void debugPostCreateResponse(ResponseEntity<ApiResult<PostCreateResponseDto>> response) {
        ApiResponseSuccessTemplate.debugApiResponseSuccess(response);
    }

    @AfterReturning(pointcut = "com.cooper.cooperposts2022.logging.pointcuts.HttpMethodPointcuts.getPointcut()", returning = "response")
    public void debugPostLookupResponse(ResponseEntity<ApiResult<PostLookupResponseDto>> response) {
        ApiResponseSuccessTemplate.debugApiResponseSuccess(response);
    }

    @AfterReturning(pointcut = "com.cooper.cooperposts2022.logging.pointcuts.HttpMethodPointcuts.putPointcut()", returning = "response")
    public void debugPostUpdateResponse(ResponseEntity<ApiResult<PostUpdateResponseDto>> response) {
        ApiResponseSuccessTemplate.debugApiResponseSuccess(response);
    }

    @AfterReturning(pointcut = "com.cooper.cooperposts2022.logging.pointcuts.HttpMethodPointcuts.deletePointcut()", returning = "response")
    public void debugPostDeleteResponse(ResponseEntity<ApiResult<Void>> response) {
        ApiResponseSuccessTemplate.debugApiResponseSuccess(response);
    }

}
