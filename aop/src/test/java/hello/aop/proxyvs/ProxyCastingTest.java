package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        var target = new MemberServiceImpl();
        var proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // Use JDK dynamic proxy

        var memberServiceProxy = (MemberService) proxyFactory.getProxy();

        log.info("proxy class={}", memberServiceProxy.getClass());

        assertThatThrownBy(() -> {
            var memberServiceImpl = (MemberServiceImpl) memberServiceProxy;
        }).isInstanceOf(ClassCastException.class);
    }

    @Test
    void cglibProxy() {
        var target = new MemberServiceImpl();
        var proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // Use CGLIB proxy

        var memberServiceProxy = (MemberService) proxyFactory.getProxy();

        log.info("proxy class={}", memberServiceProxy.getClass());

        // Casting success
        MemberServiceImpl memberServiceImpl = (MemberServiceImpl) memberServiceProxy;
    }
}
