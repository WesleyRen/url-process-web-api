package my.raptive.url.rest.repository;

public enum ProcessStatus {
    RECEIVED("RECEIVED"), SUCCESS("SUCCESS"), FAILED("FALLED"), IN_PROGRESS("IN_PROGRESS");
    public final String label;

    private ProcessStatus(String label) {
        this.label = label;
    }
}
