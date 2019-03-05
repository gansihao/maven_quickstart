package com.excelib.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLog {

    @Pointcut("execution(* com.excelib.controller.*.*(..))")
    public void pointcut() {}

//    @Before("pointcut()")
//    public void before(JoinPoint joinPoint) {
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        System.out.println("<<<AspectLog>>>开始执行方法:" + className + "." + methodName);
//    }
//
//    @After("pointcut()")
//    public void after(JoinPoint joinPoint) {
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        System.out.println("<<<AspectLog>>>方法执行完毕:" + className + "." + methodName);
//    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
    public void afterThrow(JoinPoint joinPoint, Throwable throwable) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("<<<AspectLog>>>方法执行异常:" + className + "." + methodName + " 异常信息：" + throwable.getMessage());
    }

    public AspectLog() {
        super();
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("<<<AspectLog>>>方法["+ className + "." + methodName + "]执行耗时：" + (end - start));
        return result;
    }


}
