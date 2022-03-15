package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {

    @Pointcut("execution(* hello.aop.order..*(..))") // Pointcut expression
    public void allOrder(){} // Pointcut signature

    @Pointcut("execution(* *..*Service.*(..))") // Pointcut expression
    public void allService(){} // Pointcut signature

    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}
