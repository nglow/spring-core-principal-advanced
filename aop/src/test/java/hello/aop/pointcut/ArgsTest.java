package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ArgsTest {

    Method method;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        method = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    private AspectJExpressionPointcut pointcut(String expression) {
        var pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);

        return pointcut;
    }

    @Test
    void args() {
        assertThat(pointcut("args(String)").matches(method, String.class)).isTrue();
        assertThat(pointcut("args(Object)").matches(method, String.class)).isTrue();
        assertThat(pointcut("args()").matches(method, String.class)).isFalse();
        assertThat(pointcut("args(..)").matches(method, String.class)).isTrue();
        assertThat(pointcut("args(*)").matches(method, String.class)).isTrue();
        assertThat(pointcut("args(String, ..)").matches(method, String.class)).isTrue();
    }

    @Test
    void argsVsExecution() {
        assertThat(pointcut("args(String)").matches(method, String.class)).isTrue();
        assertThat(pointcut("args(java.io.Serializable)").matches(method, String.class)).isTrue();
        assertThat(pointcut("args(Object)").matches(method, String.class)).isTrue();

        assertThat(pointcut("execution(* *(String))").matches(method, String.class)).isTrue();
        assertThat(pointcut("execution(* *(java.io.Serializable))").matches(method, String.class)).isFalse();
        assertThat(pointcut("execution(* *(Object))").matches(method, String.class)).isFalse();
    }
}
