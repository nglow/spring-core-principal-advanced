package hello.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy has been executed");
        var startTime = System.currentTimeMillis();

        var result = methodProxy.invoke(target, objects);

        var endTime = System.currentTimeMillis();
        var resultTime = endTime - startTime;
        log.info("TimeProxy has been finished, resultTime={}", resultTime);

        return result;
    }
}
