package hello.advanced.trace.hellotrace;

import org.junit.jupiter.api.Test;

class TraceV1Test {

    @Test
    void begin_end() {
        var trace = new TraceV1();
        var status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        var trace = new TraceV1();
        var status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }

}