package com.jay.blog.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAop {

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object aroundRequestMapping(ProceedingJoinPoint joinPoint) throws Throwable {
        return logCost(joinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object aroundGetMapping(ProceedingJoinPoint joinPoint) throws Throwable {
        return logCost(joinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object aroundPostMapping(ProceedingJoinPoint joinPoint) throws Throwable {
        return logCost(joinPoint);
    }

    /**
     * 记录方法执行耗时
     */
    public Object logCost(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed;
        try {
            proceed = joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            System.out.println("执行方法[" + joinPoint.getSignature() + "]耗时：" + (endTime - startTime) + " ms.");
        }
        return proceed;
    }
}
