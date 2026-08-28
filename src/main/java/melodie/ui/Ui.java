package melodie.ui;

import java.util.Scanner;

import melodie.task.Task;
import melodie.task.TaskList;

/**
 * Handles console input and output for Melodie.
 */
public class Ui {
    private static final String NAME = "Melodie";
    private static final String BANNER =
            " __  __      _           _ _\n"
            + "|  \\/  | ___| | ___   __| (_) ___\n"
            + "| |\\/| |/ _ \\ |/ _ \\ / _` | |/ _ \\\n"
            + "| |  | |  __/ | (_) | (_| | |  __/\n"
            + "|_|  |_|\\___|_|\\___/ \\__,_|_|\\___|\n"
            + "\n"
            + "          ♪  ♫  ♪";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

    public void showIntro() {
        System.out.println("____________________________________________________________\n"
                + BANNER + "\n"
                + "Hello ♪ I'm " + NAME + "~\n"
                + "What master piece shall we play?\n"
                + "____________________________________________________________\n");
    }

    public void showFarewell() {
        System.out.println("Farewell, come play with me again :D\n"
                + "____________________________________________________________\n");
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("    Sorry~ I couldn't load your saved tasks :(");
    }

    public void showSavingError() {
        System.out.println("    Sorry! I couldn't save your tasks :(");
    }

    public void showError(String message) {
        System.out.println("    " + message);
    }

    public void showTaskMarked(Task task) {
        System.out.println("    Good job! Task has been marked as done~");
        System.out.println("    " + task.toString());
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("    Task has been marked as incomplete, good luck ♫");
        System.out.println("    " + task.toString());
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("    Task has been added successfully ♪");
        System.out.println("        " + task.toString());
        System.out.println("    There are " + taskCount + " task(s) awaiting your attention~");
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("    Task has been removed ♪ goodbye task~");
        System.out.println("        " + task.toString());
        System.out.println("    There are " + taskCount + " task(s) awaiting your attention~");
    }

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

    public void close() {
        this.scanner.close();
    }
}
