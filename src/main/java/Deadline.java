import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    private static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd uuuu, h:mm a", Locale.ENGLISH);
    private final String identifier = "[D]";
    private final LocalDateTime dueDate; // deadline

    public Deadline(String description, LocalDateTime dueDate){
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Creates a deadline from the ISO date-time format used in the storage file.
     *
     * @param description description of the deadline
     * @param dueDate due date and time in ISO format
     */
    public Deadline(String description, String dueDate) {
        this(description, LocalDateTime.parse(dueDate));
    }

    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString()
                + " | " + this.dueDate.toString();
    }

    @Override
    public String toString() {
        return this.identifier + super.toString()
                + " (by: " + this.dueDate.format(DISPLAY_DATE_TIME_FORMATTER) + ")";
    }
}
