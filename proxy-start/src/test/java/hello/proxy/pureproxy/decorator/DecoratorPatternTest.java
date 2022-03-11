package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        var realComponent = new RealComponent();
        var client = new DecoratorPatternClient(realComponent);
        client.execute();
    }

    @Test
    void decorator1() {
        var realComponent = new RealComponent();
        var messageDecorator = new MessageDecorator(realComponent);
        var client = new DecoratorPatternClient(messageDecorator);

        client.execute();
    }

    @Test
    void decorator2() {
        var realComponent = new RealComponent();
        var messageDecorator = new MessageDecorator(realComponent);
        var timeDecorator = new TimeDecorator(messageDecorator);
        var client = new DecoratorPatternClient(timeDecorator);

        client.execute();
    }
}
