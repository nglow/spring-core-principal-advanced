package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        var target = new Hello();

        log.info("start");
        var result1 = target.callA();
        log.info("result={}", result1);

        log.info("start");
        var result2 = target.callB();
        log.info("result={}", result2);
    }

    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        var target = new Hello();

        var methodCallA = classHello.getMethod("callA");
        log.info("start");
        var result1 = methodCallA.invoke(target);
        log.info("result={}", result1);

        var methodCallB = classHello.getMethod("callB");
        log.info("start");
        var result2 = methodCallB.invoke(target);
        log.info("result={}", result2);
    }

    @Test
    void reflection2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        var target = new Hello();

        var methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        var methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        var result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello {

        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
