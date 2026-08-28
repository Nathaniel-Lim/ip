import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    private static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd uuuu, h:mm a", Locale.ENGLISH);
    private final String identifier = "[E]";
    private final LocalDateTime start; // 何時から、何時までですか
    private final LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an event from the ISO date-time format used in the storage file.
     *
     * @param description description of the event
     * @param start start date and time in ISO format
     * @param end end date and time in ISO format
     */
    public Event(String description, String start, String end) {
        this(description, LocalDateTime.parse(start), LocalDateTime.parse(end));
    }

    @Override
    public String toStorageString() {
        return "E | " + super.toStorageString()
                + " | " + this.start.toString()
                + " | " + this.end.toString();
    }

    @Override
    public String toString() {
        return this.identifier + super.toString()
                + " (from: " + this.start.format(DISPLAY_DATE_TIME_FORMATTER)
                + " to: " + this.end.format(DISPLAY_DATE_TIME_FORMATTER) + ")";
    }
}
