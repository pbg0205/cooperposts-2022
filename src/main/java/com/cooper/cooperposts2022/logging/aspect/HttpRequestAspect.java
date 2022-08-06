package com.cooper.cooperposts2022.logging.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Aspect
@Component
public class HttpRequestAspect {

    private static final String REQUEST_ID_KEY_NAME = "requestId";

    @Before("com.cooper.cooperposts2022.logging.pointcuts.BeanPointcuts.allControllersPointcut()")
    public void debugApiRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes()))
                .getRequest();

        String requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID_KEY_NAME, requestId);

        log.debug("[{}] request URL: {}, request method: {}", requestId, request.getRequestURI(), request.getMethod());
    }

}
