package my.raptive.url.processor.repository;

public enum ProcessStatus {
    RECEIVED("RECEIVED"), SUCCESS("SUCCESS"), FAILED("FAILED"), IN_PROGRESS("IN_PROGRESS");
    public final String label;

    private ProcessStatus(String label) {
        this.label = label;
    }
}
