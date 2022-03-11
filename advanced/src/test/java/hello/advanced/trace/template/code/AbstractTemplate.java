package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public abstract class AbstractTemplate {

    public void execute() {
        var startTime = System.currentTimeMillis();

        call();

        var endTime = System.currentTimeMillis();
        var resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    protected abstract void call();
}
