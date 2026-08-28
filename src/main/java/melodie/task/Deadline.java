package melodie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task that must be completed by a specific date and time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd uuuu, h:mm a", Locale.ENGLISH);
    private static final String IDENTIFIER = "[D]";
    private final LocalDateTime dueDate; // Deadline date and time.

    /**
     * Creates a deadline task with the specified description and due date.
     *
     * @param description Description of the deadline task.
     * @param dueDate Date and time by which the task is due.
     */
    public Deadline(String description, LocalDateTime dueDate){
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Creates a deadline from the ISO date-time format used in the storage file.
     *
     * @param description Description of the deadline task.
     * @param dueDate Due date and time in ISO format.
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
        return IDENTIFIER + super.toString()
                + " (by: " + this.dueDate.format(DISPLAY_DATE_TIME_FORMATTER) + ")";
    }
}
