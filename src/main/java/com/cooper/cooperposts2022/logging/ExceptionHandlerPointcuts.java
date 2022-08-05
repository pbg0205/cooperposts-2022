package com.cooper.cooperposts2022.logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExceptionHandlerPointcuts {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ExceptionHandler)")
    public void exceptionHandlerPointcut() {}

}
