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

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list containing a copy of the specified tasks.
     *
     * @param tasks Tasks with which to initialize the task list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the end of the task list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index Zero-based index of the task to remove.
     * @return Removed task.
     */
    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param index Zero-based index of the task to mark.
     * @return Task that was marked as completed.
     */
    public Task mark(int index) {
        Task task = this.tasks.get(index);
        task.mark();
        return task;
    }

    /**
     * Marks the task at the specified index as incomplete.
     *
     * @param index Zero-based index of the task to unmark.
     * @return Task that was marked as incomplete.
     */
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

    /**
     * Checks whether the specified index identifies a task in this list.
     *
     * @param index Zero-based index to check.
     * @return {@code true} if the index is valid, or {@code false} otherwise.
     */
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
