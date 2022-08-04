package com.cooper.cooperposts2022.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DebugAspect {

    @Pointcut("@annotation(com.cooper.cooperposts2022.logging.DebugRequired)")
    public void debugRequired() {}

    @AfterReturning(pointcut = "debugRequired()")
    public void logParam(JoinPoint joinPoint) {
        log.debug("target: {}, arguments: {}", joinPoint.getTarget().getClass().getName(), joinPoint.getArgs());
    }

    @AfterThrowing(pointcut = "debugRequired()", throwing = "exception")
    public void logException(RuntimeException exception) {
        log.debug("exceptionType: {}, message: {}", exception.getClass().getName(), exception.getMessage());
    }

}
