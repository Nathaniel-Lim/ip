package melodie.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Manages the tasks held by Melodie.
 */
public class TaskList {
    // Use arrList as it handles deletions much better
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
     * Finds tasks whose descriptions contain the specified keyword, ignoring letter case.
     *
     * @param keyword Keyword to find in task descriptions.
     * @return Matching tasks in their original task-list order.
     */
    public List<Task> find(String keyword) {
        String normalizedKeyword = keyword.toLowerCase(Locale.ROOT);
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            String normalizedDescription = task.getDescription().toLowerCase(Locale.ROOT);
            if (normalizedDescription.contains(normalizedKeyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns a read-only view of the tasks for storage operations.
     *
     * @return read-only task list
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(this.tasks);
    }
}
