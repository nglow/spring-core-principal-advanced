package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) {
        var startTime = System.currentTimeMillis();

        strategy.call();

        var endTime = System.currentTimeMillis();
        var resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
