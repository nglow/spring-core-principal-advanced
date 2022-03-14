package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v1.*;
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
public class ProxyFactoryConfigV1 {

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        var orderRepositoryV1 = new OrderRepositoryV1Impl();
        var proxyFactory = new ProxyFactory(orderRepositoryV1);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        var proxy = (OrderRepositoryV1) proxyFactory.getProxy();
        log.info("ProxyFactory={}, target={}", proxy.getClass(), orderRepositoryV1.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        var orderServiceV1 = new OrderServiceV1Impl(orderRepository(logTrace));
        var proxyFactory = new ProxyFactory(orderServiceV1);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        var proxy = (OrderServiceV1) proxyFactory.getProxy();
        log.info("ProxyFactory={}, target={}", proxy.getClass(), orderServiceV1.getClass());
        return proxy;
    }

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        var orderControllerV1 = new OrderControllerV1Impl(orderService(logTrace));
        var proxyFactory = new ProxyFactory(orderControllerV1);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        var proxy = (OrderControllerV1) proxyFactory.getProxy();
        log.info("ProxyFactory={}, target={}", proxy.getClass(), orderControllerV1.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        var pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        var advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
