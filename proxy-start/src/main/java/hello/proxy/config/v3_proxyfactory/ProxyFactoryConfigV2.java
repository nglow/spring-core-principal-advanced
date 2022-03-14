package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v1.*;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {

    @Bean
    public OrderRepositoryV2 orderRepository(LogTrace logTrace) {
        var orderRepositoryV2 = new OrderRepositoryV2();
        var proxyFactory = new ProxyFactory(orderRepositoryV2);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        var proxy = (OrderRepositoryV2) proxyFactory.getProxy();
        log.info("ProxyFactory={}, target={}", proxy.getClass(), orderRepositoryV2.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV2 orderService(LogTrace logTrace) {
        var orderServiceV2 = new OrderServiceV2(orderRepository(logTrace));
        var proxyFactory = new ProxyFactory(orderServiceV2);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        var proxy = (OrderServiceV2) proxyFactory.getProxy();
        log.info("ProxyFactory={}, target={}", proxy.getClass(), orderServiceV2.getClass());
        return proxy;
    }

    @Bean
    public OrderControllerV2 orderController(LogTrace logTrace) {
        var orderControllerV2 = new OrderControllerV2(orderService(logTrace));
        var proxyFactory = new ProxyFactory(orderControllerV2);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        var proxy = (OrderControllerV2) proxyFactory.getProxy();
        log.info("ProxyFactory={}, target={}", proxy.getClass(), orderControllerV2.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        var pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        var advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
