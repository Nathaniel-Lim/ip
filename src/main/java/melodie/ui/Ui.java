package melodie.ui;

import java.util.List;
import java.util.Scanner;

import melodie.task.Task;
import melodie.task.TaskList;

/**
 * Handles console input and output for Melodie.
 */
public class Ui {
    private static final String CHATBOT_NAME = "Melodie";
    private static final String BANNER =
            " __  __      _           _ _\n"
            + "|  \\/  | ___| | ___   __| (_) ___\n"
            + "| |\\/| |/ _ \\ |/ _ \\ / _` | |/ _ \\\n"
            + "| |  | |  __/ | (_) | (_| | |  __/\n"
            + "|_|  |_|\\___|_|\\___/ \\__,_|_|\\___|\n"
            + "\n"
            + "          ♪  ♫  ♪";
    private final Scanner scanner;

    /**
     * Creates a user interface that reads from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and trims the next command entered by the user.
     *
     * @return Command entered by the user without surrounding whitespace.
     */
    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

    /**
     * Displays Melodie's banner and greeting.
     */
    public void showIntro() {
        System.out.println("____________________________________________________________\n"
                + BANNER + "\n"
                + "Hello ♪ I'm " + CHATBOT_NAME + "~\n"
                + "What master piece shall we play?\n"
                + "____________________________________________________________\n");
    }

    /**
     * Displays Melodie's farewell message.
     */
    public void showFarewell() {
        System.out.println("Farewell, come play with me again :D\n"
                + "____________________________________________________________\n");
    }

    /**
     * Displays a line separating sections of output.
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays an error indicating that saved tasks could not be loaded.
     */
    public void showLoadingError() {
        System.out.println("    Sorry~ I couldn't load your saved tasks :(");
    }

    /**
     * Displays an error indicating that tasks could not be saved.
     */
    public void showSavingError() {
        System.out.println("    Sorry! I couldn't save your tasks :(");
    }

    /**
     * Displays the specified error message.
     *
     * @param message Error message to display.
     */
    public void showError(String message) {
        System.out.println("    " + message);
    }

    /**
     * Displays confirmation that a task was marked as completed.
     *
     * @param task Task that was marked as completed.
     */
    public void showTaskMarked(Task task) {
        System.out.println("    Good job! Task has been marked as done~");
        System.out.println("    " + task.toString());
    }

    /**
     * Displays confirmation that a task was marked as incomplete.
     *
     * @param task Task that was marked as incomplete.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("    Task has been marked as incomplete, good luck ♫");
        System.out.println("    " + task.toString());
    }

    /**
     * Displays confirmation that a task was added and the updated task count.
     *
     * @param task Task that was added.
     * @param taskCount Number of tasks after the addition.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("    Task has been added successfully ♪");
        System.out.println("        " + task.toString());
        System.out.println("    There are " + taskCount + " task(s) awaiting your attention~");
    }

    /**
     * Displays confirmation that a task was deleted and the updated task count.
     *
     * @param task Task that was deleted.
     * @param taskCount Number of tasks after the deletion.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("    Task has been removed ♪ goodbye task~");
        System.out.println("        " + task.toString());
        System.out.println("    There are " + taskCount + " task(s) awaiting your attention~");
    }

    /**
     * Displays every task in the task list with its user-facing number.
     *
     * @param tasks Tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("    Your list is currently empty; let's get started shall we? ♪");
            return;
        }

        System.out.println("    Here are the tasks in your list ♪");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Closes the input scanner used by the user interface.
     */
    public void close() {
        this.scanner.close();
    }

    /**
     * Displays tasks whose descriptions match a search keyword.
     *
     * @param matchingTasks Tasks matching the search keyword.
     */
    public void showMatchingTasks(List<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("    I couldn't find any matching tasks :(");
            return;
        }

        System.out.println("    Here are the matching tasks in your list ♪");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + matchingTasks.get(i).toString());
        }
    }
}
