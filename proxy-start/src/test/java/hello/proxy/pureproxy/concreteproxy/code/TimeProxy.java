package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {

    private ConcreteLogic realLogic;

    public TimeProxy(ConcreteLogic realLogic) {
        this.realLogic = realLogic;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator has been executed");
        var startTime = System.currentTimeMillis();

        var result = realLogic.operation();

        var endTime = System.currentTimeMillis();
        var timeTaken = endTime - startTime;
        log.info("TimeDecorator has been finished. time-taken={}", timeTaken);

        return result;
    }
}
