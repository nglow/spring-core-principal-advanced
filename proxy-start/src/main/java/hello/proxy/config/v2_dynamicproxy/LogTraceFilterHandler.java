package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;
    private final String[] pattern;

    public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] pattern) {
        this.target = target;
        this.logTrace = logTrace;
        this.pattern = pattern;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var methodName = method.getName();

        if (!PatternMatchUtils.simpleMatch(pattern, methodName)) {
            return method.invoke(target, args);
        }

        TraceStatus status = null;
        try {
            var message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTrace.begin(message);
            var result = method.invoke(target, args);
            logTrace.end(status);

            return result;
        } catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
