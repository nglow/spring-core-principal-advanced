package hello.advanced.trace;

public class TraceStatus {

    private TraceId traceId;
    private Long startTimeMillis;
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMillis, String message) {
        this.traceId = traceId;
        this.startTimeMillis = startTimeMillis;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public String getMessage() {
        return message;
    }

    public Long getStartTimeMillis() {
        return startTimeMillis;
    }
}
