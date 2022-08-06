package com.cooper.cooperposts2022.logging.aspect;

import com.cooper.cooperposts2022.common.ApiResult;
import com.cooper.cooperposts2022.logging.template.ApiResponseFailTemplate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class HttpResponseFailAspect {

    @AfterReturning(pointcut = "com.cooper.cooperposts2022.logging.pointcuts.ExceptionHandlerPointcuts.exceptionHandlerPointcut()", returning = "response")
    public void debugFailResponse(ResponseEntity<ApiResult<String>> response) {
        ApiResponseFailTemplate.debugApiResponseFail(response);
    }

}
