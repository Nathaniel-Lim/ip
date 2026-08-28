package melodie.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import melodie.MelodieException;
import melodie.task.Deadline;
import melodie.task.Event;
import melodie.task.Task;
import melodie.task.Todo;

/**
 * Interprets user commands and converts their arguments into task data.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/uuuu HHmm")
                    .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Parses raw user input into a command and its arguments.
     *
     * @param input Raw command entered by the user.
     * @return Parsed command and arguments.
     * @throws MelodieException If the command word is not recognized.
     */
    public ParsedCommand parse(String input) throws MelodieException {
        String[] parts = input.trim().split(" ", 2);
        Command command = Command.from(parts[0]);
        String arguments = parts.length > 1 ? parts[1] : "";
        return new ParsedCommand(command, arguments);
    }

    /**
     * Converts the arguments of a task-creation command into the matching task type.
     *
     * @param parsedCommand Task-creation command and its arguments.
     * @return Task created from the command arguments.
     * @throws MelodieException If the command does not create a task or its arguments are invalid.
     */
    public Task parseTask(ParsedCommand parsedCommand) throws MelodieException {
        try {
            switch (parsedCommand.getCommand()) {
                case TODO:
                    return this.parseTodo(parsedCommand.getArguments());
                case DEADLINE:
                    return this.parseDeadline(parsedCommand.getArguments());
                case EVENT:
                    return this.parseEvent(parsedCommand.getArguments());
                default:
                    throw new MelodieException("This command does not create a task :(");
            }
        } catch (DateTimeParseException e) {
            throw new MelodieException("Please enter the date and time in d/M/yyyy HHmm format :(\n"
                    + "    Example: 2/12/2019 1800");
        }
    }

    /**
     * Converts a user-provided task number into a zero-based list index.
     *
     * @param arguments Task number entered by the user.
     * @return Zero-based index of the specified task.
     * @throws MelodieException If the task number is not a valid integer.
     */
    public int parseTaskIndex(String arguments) throws MelodieException {
        try {
            return Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new MelodieException("Please enter a valid task number :(");
        }
    }

    /**
     * Creates a todo task from its command arguments.
     *
     * @param taskDescription Description of the todo task.
     * @return Todo task with the specified description.
     * @throws MelodieException If the description is blank.
     */
    private Todo parseTodo(String taskDescription) throws MelodieException {
        if (taskDescription.isBlank()) {
            throw new MelodieException("You can't leave the description of a todo empty :(");
        }
        return new Todo(taskDescription);
    }

    /**
     * Creates a deadline task from its description and due date arguments.
     *
     * @param taskDescription Deadline description and due date arguments.
     * @return Deadline task created from the arguments.
     * @throws MelodieException If the description or due date is missing.
     */
    private Deadline parseDeadline(String taskDescription) throws MelodieException {
        String[] deadlineParts = taskDescription.split("/by ", 2);
        if (deadlineParts.length != 2
                || deadlineParts[0].isBlank()
                || deadlineParts[1].isBlank()) {
            throw new MelodieException("Please enter a valid task description, due date, and time :(\n"
                    + "    Format: deadline <task description> /by <d/M/yyyy HHmm>\n"
                    + "    Example: deadline return book /by 2/12/2019 1800");
        }

        String description = deadlineParts[0].trim();
        String dueDateString = deadlineParts[1].trim();
        LocalDateTime dueDate = LocalDateTime.parse(
                dueDateString, INPUT_DATE_TIME_FORMATTER);
        return new Deadline(description, dueDate);
    }

    /**
     * Creates an event task from its description, start date, and end date arguments.
     *
     * @param taskDescription Event description, start date, and end date arguments.
     * @return Event task created from the arguments.
     * @throws MelodieException If an argument is missing or the event ends before it starts.
     */
    private Event parseEvent(String taskDescription) throws MelodieException {
        String[] fromParts = taskDescription.split("/from ", 2);
        if (fromParts.length != 2
                || fromParts[0].isBlank()) {
            throw new MelodieException(
                    "Please enter a valid task description, start date and time, and end date and time :(\n"
                    + "    Format: event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>\n"
                    + "    Example: event project meeting /from 2/12/2019 1400 /to 2/12/2019 1600");
        }

        String[] toParts = fromParts[1].split("/to ", 2);
        if (toParts.length != 2
                || toParts[0].isBlank()
                || toParts[1].isBlank()) {
            throw new MelodieException(
                    "Please enter a valid task description, start date and time, and end date and time :(\n"
                    + "    Format: event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>\n"
                    + "    Example: event project meeting /from 2/12/2019 1400 /to 2/12/2019 1600");
        }

        String description = fromParts[0].trim(); // there has to be a more scalable way
        String startString = toParts[0].trim();
        String endString = toParts[1].trim();
        LocalDateTime start = LocalDateTime.parse(
                startString, INPUT_DATE_TIME_FORMATTER);
        LocalDateTime end = LocalDateTime.parse(
                endString, INPUT_DATE_TIME_FORMATTER);
        if (end.isBefore(start)) {
            throw new MelodieException("The event cannot end before it starts :(");
        }
        return new Event(description, start, end);
    }
}
