package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        var startTime = System.currentTimeMillis();

        log.info("Execute biz logic1");

        var endTime = System.currentTimeMillis();
        var resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        var startTime = System.currentTimeMillis();

        log.info("Execute biz logic2");

        var endTime = System.currentTimeMillis();
        var resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
    void strategyV1() {
        Strategy strategyLogic1 = new StrategyLogic1();
        var context1 = new ContextV1(strategyLogic1);
        context1.execute();

        Strategy strategyLogic2 = new StrategyLogic2();
        var context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    @Test
    void strategyV2() {
        var strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("Execute logic1");
            }
        };

        log.info("strategyLogic1={}", strategyLogic1.getClass());
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        var strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("Execute logic2");
            }
        };

        log.info("strategyLogic2={}", strategyLogic2.getClass());
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    @Test
    void strategyV3() {
        var context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("Execute logic1");
            }
        });
        context1.execute();

        var context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("Execute logic2");
            }
        });
        context2.execute();
    }

    @Test
    void strategyV4() {
        var context1 = new ContextV1(() -> log.info("Execute logic1"));
        context1.execute();

        var context2 = new ContextV1(() -> log.info("Execute logic2"));
        context2.execute();
    }

}
