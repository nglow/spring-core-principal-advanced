package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.template.Callback;
import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    @Test
    void callbackV1() {
        var template = new TimeLogTemplate();
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("Execute biz logic1");
            }
        });
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("Execute biz logic2");
            }
        });
    }

    @Test
    void callbackV2() {
        var template = new TimeLogTemplate();
        template.execute(() -> log.info("Execute biz logic1"));
        template.execute(() -> log.info("Execute biz logic2"));
    }
}
