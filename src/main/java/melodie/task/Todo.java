package melodie.task;

/**
 * Represents a task without an associated date or time.
 */
public class Todo extends Task {
    private final String identifier = "[T]";
    // description in the super class

    /**
     * Creates a todo task with the specified description.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns this todo task in the format used by the storage file.
     *
     * @return Storage-file representation of this todo task.
     */
    @Override
    public String toStorageString() {
        return "T | " + super.toStorageString();
    }

    /**
     * Returns this todo task in the format displayed in the user interface.
     *
     * @return User-facing string representation of this todo task.
     */
    @Override
    public String toString() {
        return this.identifier + super.toString();
    }
}
