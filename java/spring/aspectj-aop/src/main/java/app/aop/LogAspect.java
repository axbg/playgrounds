package app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    // private methods can be targeted when using ajc (not spring proxy)
    @Pointcut("execution(private * app.service.BaseService.*(..))")
    public void log() {
    }

    // argNames not required when using ajc
    @Before(value = "log()")
    public void before(JoinPoint joinPoint) {
        System.out.println("*** Before execution hit");
    }

    // argNames not required when using ajc
    @After(value = "log()")
    public void after(JoinPoint joinPoint) {
        System.out.println("*** After execution hit");
    }
}
