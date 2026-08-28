package melodie;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import melodie.command.Command;
import melodie.command.ParsedCommand;
import melodie.command.Parser;
import melodie.storage.Storage;
import melodie.task.Task;
import melodie.task.TaskList;
import melodie.ui.Ui;

/**
 * Coordinates the components of the Melodie chatbot.
 */
public class Melodie {
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private TaskList tasks;

    public Melodie() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser();
        this.tasks = new TaskList();
    }

    public void run() {
        this.ui.showIntro();
        this.loadTasks();

        while (true) {
            try {
                ParsedCommand parsedCommand = this.parser.parse(this.ui.readCommand());
                this.ui.showLine();

                if (parsedCommand.getCommand() == Command.BYE) { // does not change the list
                    break;
                }

                this.executeCommand(parsedCommand);
            } catch (MelodieException e) {
                this.ui.showError(e.getMessage());
            } catch (IOException e) {
                this.ui.showSavingError();
            }
            this.ui.showLine();
        }

        this.ui.showFarewell();
        this.ui.close();
    }

    private void loadTasks() {
        try {
            this.tasks = new TaskList(this.storage.read());
        } catch (IOException | DateTimeParseException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    private void executeCommand(ParsedCommand parsedCommand) throws MelodieException, IOException {
        switch (parsedCommand.getCommand()) {
            case MARK:
            case UNMARK:
            case DELETE:
                int taskIndex = this.parser.parseTaskIndex(parsedCommand.getArguments());
                if (!this.tasks.isValidIndex(taskIndex)) {
                    throw new MelodieException("Please enter a valid task number :(");
                }

                if (parsedCommand.getCommand() == Command.MARK) {
                    Task markedTask = this.tasks.mark(taskIndex);
                    this.storage.write(this.tasks);
                    this.ui.showTaskMarked(markedTask);
                } else if (parsedCommand.getCommand() == Command.UNMARK) {
                    Task unmarkedTask = this.tasks.unmark(taskIndex);
                    this.storage.write(this.tasks);
                    this.ui.showTaskUnmarked(unmarkedTask);
                } else {
                    Task deletedTask = this.tasks.delete(taskIndex);
                    this.storage.write(this.tasks);
                    this.ui.showTaskDeleted(deletedTask, this.tasks.size());
                }
                break;

            case TODO:
            case DEADLINE:
            case EVENT:
                Task task = this.parser.parseTask(parsedCommand);
                this.tasks.add(task);
                this.storage.write(this.tasks);
                this.ui.showTaskAdded(task, this.tasks.size());
                break;

            case LIST: // does not change the list
                this.ui.showTaskList(this.tasks);
                break;

            default:
                throw new MelodieException("Sorry~ I don't recognise that command :(");
        }
    }

    public static void main(String[] args) {
        new Melodie().run();
    }
}
