package com.cooper.cooperposts2022.logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class HttpMethodPointcuts {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postPointcut() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getPointcut() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putPointcut() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deletePointcut() {}

    @Pointcut("postPointcut() || getPointcut() || putPointcut() || deletePointcut()")
    public void allHttpMethodsPointcut() {}

}
