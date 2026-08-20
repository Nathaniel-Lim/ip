public class Deadline extends Task {
    private final String identifier = "[D]";
    private String dueDate = null; // deadline

    public Deadline(String description, String dueDate){
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return this.identifier + super.toString() + " (by: " + this.dueDate + ")";
    }
}
