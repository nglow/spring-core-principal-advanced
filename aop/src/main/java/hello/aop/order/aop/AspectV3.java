package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class AspectV3 {

    @Pointcut("execution(* hello.aop.order..*(..))") // Pointcut expression
    private void allOrder(){} // Pointcut signature

    @Pointcut("execution(* *..*Service.*(..))") // Pointcut expression
    private void allService(){} // Pointcut signature

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // Join Point Signature
        return joinPoint.proceed();
    }

    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[Transaction-start] {}", joinPoint.getSignature());
            var result = joinPoint.proceed();
            log.info("[Transaction-commit] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[Transaction-rollback] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[Resource-release] {}", joinPoint.getSignature());
        }
    }
}
