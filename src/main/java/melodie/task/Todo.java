package melodie.task;

public class Todo extends Task {
    private static final String IDENTIFIER = "[T]";
    // The description is stored in the superclass.

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStorageString() {
        return "T | " + super.toStorageString();
    }

    @Override
    public String toString() {
        return IDENTIFIER + super.toString();
    }
}
