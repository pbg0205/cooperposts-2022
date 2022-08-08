package com.cooper.cooperposts2022.logging.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class StopWatchAspect {

    private static final String REQUEST_ID_KEY_NAME = "requestId";

    @Around("com.cooper.cooperposts2022.logging.pointcuts.StopWatchPointcuts.stopWatchPointcut()")
    public Object debugExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        log.debug("[{}] target: {}, elapsed milli time : {}",
                MDC.get(REQUEST_ID_KEY_NAME),
                joinPoint.getTarget(),
                stopWatch.getTotalTimeMillis()
        );

        return result;
    }

}
