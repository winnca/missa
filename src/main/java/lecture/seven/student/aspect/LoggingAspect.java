package lecture.seven.student.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* lecture.seven.student.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(">>> " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* lecture.seven.student.service.*.*(..))",
            returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        System.out.println("<<< " + joinPoint.getSignature().getName() + " => " + result);
    }
}
