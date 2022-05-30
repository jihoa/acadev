package com.asianaidt.springmvc.step03.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * <pre>
 * com.asianaidt.springmvc.step03.common
 * L PerformanceAop.java
 * Spring AOP 사용하기
 * 필요라이브러리 : spring-boot-starter-aop
 * @SpringBootApplication이  선언된 클래스에 @EnableAspectJAutoProxy 선언
 * 적용할 AOP에 @Aspect 선언하고 Spring Bean 으로 등록하기 위해 @Component 추가
 * 주요 용어 : Aspect(사용 선언), PointCut(Aspect를 적용할 지점), Advice(수행코드)
 * @date : 2021-05-24 오후 5:47
 * @author : YHKIM
 **/
@Slf4j
@Aspect
@Component
public class PerformanceAop {
    @Around("execution(* *..*ServiceImpl.*(..))")
    public Object performanceChek(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        Object proceed = null;
        String methodName = joinPoint.getSignature().getName();
        try {
            stopWatch.start();
            proceed = joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info("{} time : {} ms", methodName, stopWatch.getTotalTimeMillis());
        }
        return proceed;
    }
}
