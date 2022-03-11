package hello.advanced.trace.hellotrace;

import org.junit.jupiter.api.Test;

class TraceV2Test {

    @Test
    void begin_end() {
        var trace = new TraceV2();
        var status1 = trace.begin("hello1");
        var status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        var trace = new TraceV2();
        var status1 = trace.begin("hello1");
        var status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

}