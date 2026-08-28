package melodie.task;

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
        this.isCompleted = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public String getStatusCode() {
        return this.isCompleted ? "1" : "0";
    }

    public String toStorageString() {
        return this.getStatusCode() + " | " + this.description;
    }

    @Override
    public String toString() {
        String mark = " ";
        if (this.isCompleted) {
            mark = "X";
        }
        return "[" + mark + "] " + this.description;
    }
}
