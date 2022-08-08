package com.cooper.cooperposts2022.logging.pointcuts;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class StopWatchPointcuts {

    @Pointcut("@annotation(com.cooper.cooperposts2022.logging.annotation.StopWatchRequired)")
    public void stopWatchPointcut() {}

}
