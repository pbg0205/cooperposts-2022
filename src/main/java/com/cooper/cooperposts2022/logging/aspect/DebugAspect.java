package com.cooper.cooperposts2022.logging.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DebugAspect {

    private static final String REQUEST_ID_KEY_NAME = "requestId";

    @Pointcut("@annotation(com.cooper.cooperposts2022.logging.annotation.DebugRequired)")
    public void debugRequired() {}

    @AfterReturning(pointcut = "debugRequired()")
    public void logParam(JoinPoint joinPoint) {
        String requestId = MDC.get(REQUEST_ID_KEY_NAME);
        log.debug("[{}] target: {}, arguments: {}",
                requestId,
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getArgs()
        );
    }

    @AfterThrowing(pointcut = "debugRequired()", throwing = "exception")
    public void logException(RuntimeException exception) {
        String requestId = MDC.get(REQUEST_ID_KEY_NAME);
        log.debug("[{}] exceptionType: {}, message: {}",
                requestId,
                exception.getClass().getName(),
                exception.getMessage()
        );
    }

}
