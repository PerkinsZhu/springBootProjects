package com.perkins.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class DataSourceAspect {

    @Pointcut("execution(public * com.perkins.mapper..*.*(..))")
    public void declareJointPointExpression() {
    }

    @Before("declareJointPointExpression()")
    public void setDataSourceKey(JoinPoint point) {
        System.out.printf("========befor============");
        System.out.printf("========可在这里更新数据源============");
    }

}
