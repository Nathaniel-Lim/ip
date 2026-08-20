public class Task {
    private final String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String mark = " ";
        if (this.completed == true) {
            mark = "X";
        }
        return "[" + mark + "] " + description;
    }
}
