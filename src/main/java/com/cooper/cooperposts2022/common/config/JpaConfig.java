package com.cooper.cooperposts2022.common.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

@Configuration
public class JpaConfig {

    @Bean
    public Advisor transactionAdviceAdvisor(TransactionManager transactionManager) {
        Properties properties = new Properties();
        properties.put("*", "PROPAGATION_REQUIRED");
        properties.put("find*", "PROPAGATION_REQUIRED,readOnly");

        NameMatchTransactionAttributeSource txSource = new NameMatchTransactionAttributeSource();
        txSource.setProperties(properties);

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com..*.*Service.*(..))");

        return new DefaultPointcutAdvisor(pointcut, new TransactionInterceptor(transactionManager, txSource));
    }

}