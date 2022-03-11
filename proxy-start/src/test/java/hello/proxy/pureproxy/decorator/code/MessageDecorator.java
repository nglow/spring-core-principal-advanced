package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component{
    
    private Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator has been executed");

        var result = component.operation();
        var resultDecorated = "*****" + result + "*****";
        log.info("MessageDecorator before={}, after={}", result, resultDecorated);

        return resultDecorated;
    }
}
