package io.khasang.pm.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Aspect
public class Bard {

//    @Pointcut("execution(* io.khasang.pm.service.impl.KnightServiceImpl.getAchievement(..))")
//    public void serviceBefore() {
//    }
//
//    @Before(value = "serviceBefore()")
//    public void getSong(JoinPoint joinPoint) {
//        System.err.println("Lalalalala!!!");
//    }

    @Around("execution(* io.khasang.pm.service.impl.KnightServiceImpl.getAchievement(..)) && args(val, ..)")
    public Object action(ProceedingJoinPoint joinPoint, String val) throws Throwable {
        long timeBefore = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        long timeAfter = System.currentTimeMillis();
        System.err.println("LALALALALAAA!!!");

        System.err.println("Knight defeat an enemy - " + val + " with " + ((timeAfter - timeBefore) / 1000) + "s");
        return obj;
    }
}
