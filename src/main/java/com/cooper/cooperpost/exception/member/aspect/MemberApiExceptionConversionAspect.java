package com.cooper.cooperpost.exception.member.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.cooper.cooperpost.exception.member.MemberApiException;
import com.cooper.cooperpost.exception.member.MemberException;

@Aspect
@Component
public class MemberApiExceptionConversionAspect {

	@Pointcut(value = "execution(* com.cooper.cooperpost.presentation.member..*(..))")
	private void memberControllerPointcut() {}

	@Pointcut(value = "execution(* com.cooper.cooperpost.business.member..*(..))")
	private void memberServicePointcut() {}

	@Pointcut(value = "execution(* com.cooper.cooperpost.persistence.member..*(..))")
	private void memberRepositoryPointcut() {}

	@AfterThrowing(value = "memberControllerPointcut() || memberServicePointcut() || memberRepositoryPointcut()",
		throwing = "exception")
	public void convertMemberApiException(JoinPoint joinPoint, Exception exception) throws RuntimeException {
		if (exception instanceof MemberException) {
			throw new MemberApiException(exception);
		}
	}
}
