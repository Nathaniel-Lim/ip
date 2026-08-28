package melodie.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the tasks held by Melodie.
 */
public class TaskList {
    // ArrayList supports the indexed operations used by task commands.
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    public Task mark(int index) {
        Task task = this.tasks.get(index);
        task.mark();
        return task;
    }

    public Task unmark(int index) {
        Task task = this.tasks.get(index);
        task.unmark();
        return task;
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < this.tasks.size();
    }

    /**
     * Returns a read-only view of the tasks for storage operations.
     *
     * @return Read-only task list.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(this.tasks);
    }
}
