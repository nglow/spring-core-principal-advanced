package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    void basicConfig() {
        var applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);
        var a = applicationContext.getBean("beanA", A.class);

//        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
//                () -> applicationContext.getBean(B.class));
        Assertions.assertThatThrownBy(() -> applicationContext.getBean(B.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Configuration
    @Slf4j
    static class BasicConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
    }

    @Slf4j
    static class A {
        public void helloA() {
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }
    }
}
