public class Todo extends Task {
    private final String identifier = "[T]";
    // description in the super class

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return this.identifier + super.toString();
    }
}
