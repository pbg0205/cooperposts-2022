package com.cooper.cooperposts2022.logging.pointcuts;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BeanPointcuts {

    @Pointcut("bean(*Controller)")
    public void allControllersPointcut() {}

}
