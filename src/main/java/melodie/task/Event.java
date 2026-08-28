package melodie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task that occurs between a start date and an end date.
 */
public class Event extends Task {
    private static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd uuuu, h:mm a", Locale.ENGLISH);
    private static final String IDENTIFIER = "[E]";
    private final LocalDateTime start; // Event start date and time.
    private final LocalDateTime end;

    /**
     * Creates an event task with the specified description, start date, and end date.
     *
     * @param description Description of the event task.
     * @param start Date and time at which the event starts.
     * @param end Date and time at which the event ends.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an event from the ISO date-time format used in the storage file.
     *
     * @param description Description of the event task.
     * @param start Start date and time in ISO format.
     * @param end End date and time in ISO format.
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
        return IDENTIFIER + super.toString()
                + " (from: " + this.start.format(DISPLAY_DATE_TIME_FORMATTER)
                + " to: " + this.end.format(DISPLAY_DATE_TIME_FORMATTER) + ")";
    }
}
