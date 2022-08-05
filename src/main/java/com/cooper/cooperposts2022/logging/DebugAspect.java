package com.cooper.cooperposts2022.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
public class DebugAspect {

    @Pointcut("@annotation(com.cooper.cooperposts2022.logging.DebugRequired)")
    public void debugRequired() {}

    @Before("debugRequired()")
    public void logRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes()))
                .getRequest();

        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);

        log.debug("[{}] request URL: {}, request method: {}", requestId, request.getRequestURI(), request.getMethod());
    }

    @AfterReturning(pointcut = "debugRequired()")
    public void logParam(JoinPoint joinPoint) {
        log.debug("target: {}, arguments: {}", joinPoint.getTarget().getClass().getName(), joinPoint.getArgs());
    }

    @AfterThrowing(pointcut = "debugRequired()", throwing = "exception")
    public void logException(RuntimeException exception) {
        log.debug("exceptionType: {}, message: {}", exception.getClass().getName(), exception.getMessage());
    }

}
