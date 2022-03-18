package hello.aop.selfinvocation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CallServiceV0 {

    public void external() {
        log.info("Call external");
        internal(); // Self invocation;
    }

    public void internal() {
        log.info("Call internal");
    }
}
