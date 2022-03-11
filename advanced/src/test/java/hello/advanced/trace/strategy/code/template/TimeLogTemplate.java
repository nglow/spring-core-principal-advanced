package hello.advanced.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

    public void execute(Callback callback) {
        var startTime = System.currentTimeMillis();

        callback.call();

        var endTime = System.currentTimeMillis();
        var resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
