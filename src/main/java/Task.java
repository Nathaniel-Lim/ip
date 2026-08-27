public class Task {
    private final String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void mark() {
        this.isCompleted = true;
    }

    public void unmark() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String mark = " ";
        if (this.isCompleted) {
            mark = "X";
        }
        return "[" + mark + "] " + description;
    }
}
