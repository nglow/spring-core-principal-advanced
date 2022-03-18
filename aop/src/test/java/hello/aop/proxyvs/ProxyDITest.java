package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.proxyvs.code.ProxyDiAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) // Use JDK dynamic proxy
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) // Use CGLIB proxy
@SpringBootTest
@Import(ProxyDiAspect.class)
@Slf4j
public class ProxyDITest {

    @Autowired
    MemberService memberService; // JDK dynamic proxy available, CGLIB available

    @Autowired
    MemberServiceImpl memberServiceImpl; // JDK dynamic proxy unavailable, CGLIB available

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }

}
