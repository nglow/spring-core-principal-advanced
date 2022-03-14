package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    public static final String[] PATTERN = {"request*", "order*", "save*"};

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        var orderRepositoryV1 = new OrderRepositoryV1Impl();

        return (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTraceFilterHandler(orderRepositoryV1, logTrace, PATTERN));
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        var orderServiceV1 = new OrderServiceV1Impl(orderRepository(logTrace));

        return (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceFilterHandler(orderServiceV1, logTrace, PATTERN));
    }

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        var orderControllerV1 = new OrderControllerV1Impl(orderService(logTrace));

        return (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceFilterHandler(orderControllerV1, logTrace, PATTERN));
    }
}
