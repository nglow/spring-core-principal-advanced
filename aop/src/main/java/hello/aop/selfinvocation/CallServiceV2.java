package hello.aop.selfinvocation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CallServiceV2 {

//    private final ApplicationContext applicationContext;
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public void external() {
        log.info("Call external");
//        var callServiceV2 = applicationContext.getBean(CallServiceV2.class);
        var callServiceV2 = callServiceProvider.getObject();
        callServiceV2.internal();
    }

    public void internal() {
        log.info("Call internal");
    }
}
