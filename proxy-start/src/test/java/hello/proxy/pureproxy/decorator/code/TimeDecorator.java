package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component{

    private Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator has been executed");

        var startTime = System.currentTimeMillis();
        var result = component.operation();
        log.info("time-taken={}", System.currentTimeMillis() - startTime);

        return result;
    }
}
