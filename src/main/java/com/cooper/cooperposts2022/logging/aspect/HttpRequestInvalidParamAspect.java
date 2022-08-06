package com.cooper.cooperposts2022.logging.aspect;

import com.cooper.cooperposts2022.common.ApiResult;
import com.cooper.cooperposts2022.logging.template.ApiResponseFailTemplate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Aspect
@Component
public class HttpRequestInvalidParamAspect {

    @AfterReturning(pointcut = "com.cooper.cooperposts2022.logging.pointcuts.ExceptionHandlerPointcuts.exceptionHandlerPointcut()", returning = "invalidParamResponse")
    public void debugInvalidRequest(ResponseEntity<ApiResult<Map<String, String>>> invalidParamResponse) {
        ApiResponseFailTemplate.debugApiResponseFail(invalidParamResponse);
    }

}
