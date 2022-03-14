package hello.proxy.advisor;

import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

@Slf4j
public class MultiAdvisorTest {

    @Test
    @DisplayName("Multi proxy")
    void multiAdvisorTest1() {
        var target = new ServiceImpl();
        var proxyFactory1 = new ProxyFactory(target);
        var advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, (MethodInterceptor) invocation -> {
            log.info("Advice1 has been invoked");
            return invocation.proceed();
        });
        proxyFactory1.addAdvisor(advisor1);
        var proxy1 = (ServiceInterface) proxyFactory1.getProxy();

        var proxyFactory2 = new ProxyFactory(proxy1);
        var advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, (MethodInterceptor) invocation -> {
            log.info("Advice2 has been invoked");
            return invocation.proceed();
        });
        proxyFactory2.addAdvisor(advisor2);
        var proxy2 = (ServiceInterface) proxyFactory2.getProxy();

        proxy2.save();
    }

    @Test
    @DisplayName("One proxy - multi advisor")
    void multiAdvisorTest2() {
        var advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, (MethodInterceptor) invocation -> {
            log.info("Advice2 has been invoked");
            return invocation.proceed();
        });
        var advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, (MethodInterceptor) invocation -> {
            log.info("Advice1 has been invoked");
            return invocation.proceed();
        });

        var target = new ServiceImpl();
        var proxyFactory1 = new ProxyFactory(target);
        proxyFactory1.addAdvisor(advisor2);
        proxyFactory1.addAdvisor(advisor1);
        var proxy = (ServiceInterface) proxyFactory1.getProxy();

        proxy.save();
    }
}
