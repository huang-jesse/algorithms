import java.time.Duration;
import java.time.Instant;

public class Timer {
    private final Instant start;
    public Timer() {
        this.start = Instant.now();
    }

    /**
     * elapsed milliseconds
     * @return
     */
    public long elapsed() {
        return Duration.between(start, Instant.now()).toMillis();
    }
}
