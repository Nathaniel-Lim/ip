import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Melodie {
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/uuuu HHmm")
                    .withResolverStyle(ResolverStyle.STRICT);
    public static String name = "Melodie";
    public static String banner =
            " __  __      _           _ _\n" +
            "|  \\/  | ___| | ___   __| (_) ___\n" +
            "| |\\/| |/ _ \\ |/ _ \\ / _` | |/ _ \\\n" +
            "| |  | |  __/ | (_) | (_| | |  __/\n" +
            "|_|  |_|\\___|_|\\___/ \\__,_|_|\\___|\n" +
            "\n" +
            "          ♪  ♫  ♪";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(printIntro());

        // Use arrList as it handles deletions much better
        Storage storage = new Storage();
        ArrayList<Task> tasks;

        try {
            tasks = storage.read();
        } catch (IOException | DateTimeParseException e) {
            System.out.println("    Sorry~ I couldn't load your saved tasks :(");
            tasks = new ArrayList<>();
        }

        while (true) {
            try {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            Command command = Command.from(parts[0]);
            String taskDescription = parts.length > 1 ? parts[1] : "";

            System.out.println("    ____________________________________________________________");

            if (command == Command.BYE) { // does not change the list
                break;
            }
                switch (command) {
                    case MARK:
                    case UNMARK:
                    case DELETE:
                        int taskNum = Integer.parseInt(taskDescription) - 1;
                        if (taskNum < 0 || taskNum >= tasks.size()) {
                            throw new MelodieException("Please enter a valid task number :(");
                        }

                        if (command == Command.MARK) {
//                            tasks[taskNum].mark();
                            tasks.get(taskNum).mark();
                            storage.write(tasks);
                            System.out.println("    Good job! Task has been marked as done~");
                            System.out.println("    " + tasks.get(taskNum).toString());
                        } else if (command == Command.UNMARK){
//                            tasks[taskNum].unmark();
                            tasks.get(taskNum).unmark();
                            storage.write(tasks);
                            System.out.println("    Task has been marked as incomplete, good luck ♫");
                            System.out.println("    " + tasks.get(taskNum).toString());
                        } else {
                            Task toDelete = tasks.remove(taskNum);
                            storage.write(tasks);
                            printDeleteTaskDetails(toDelete, tasks.size());
                        }

                        break;

                    case TODO:
                        if (taskDescription.isBlank()) {
                            throw new MelodieException("You can't leave the description of a todo empty :(");
                        }
                        Task todo = new Todo(taskDescription);
                        tasks.add(todo);
                        storage.write(tasks);
                        printTaskDetails(todo, tasks.size());
                        break;

                    case DEADLINE:
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
                        Task deadline = new Deadline(description, dueDate);
                        tasks.add(deadline);
                        storage.write(tasks);
                        printTaskDetails(deadline, tasks.size());
                        break;

                    case EVENT:
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

                        String descriptions = fromParts[0].trim(); // there has to be a more scalable way
                        String startString = toParts[0].trim();
                        String endString = toParts[1].trim();
                        LocalDateTime start = LocalDateTime.parse(
                                startString, INPUT_DATE_TIME_FORMATTER);
                        LocalDateTime end = LocalDateTime.parse(
                                endString, INPUT_DATE_TIME_FORMATTER);
                        if (end.isBefore(start)) {
                            throw new MelodieException("The event cannot end before it starts :(");
                        }
                        Task event = new Event(descriptions, start, end);
                        tasks.add(event);
                        storage.write(tasks);
                        printTaskDetails(event, tasks.size());
                        break;

                    case LIST: // does not change the list
                        if (tasks.isEmpty()) {
                            System.out.println("    Your list is currently empty; let's get started shall we? ♪");
                            break;
                        }
                        System.out.println("    Here are the tasks in your list ♪");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println("    " + (i + 1) + ". " + tasks.get(i).toString());
                        }
                        break;

                    default:
                        throw new MelodieException("Sorry~ I don't recognise that command :(");
//                    tasks[taskCounter++] = new Task(input);
//                    System.out.println("    added: " + input);
                }
            } catch (MelodieException e) {
                System.out.println("    " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("    Please enter a valid task number :(");
            } catch (DateTimeParseException e) {
                System.out.println("    Please enter the date and time in d/M/yyyy HHmm format :(\n"
                                   + "    Example: 2/12/2019 1800");
            } catch (IOException e) {
                System.out.println("    Sorry! I couldn't save your tasks :(");
            }
            System.out.println("    ____________________________________________________________");
        }

        System.out.println(printFarewell());
        scanner.close();
    }

    public static String printIntro() {
        return "____________________________________________________________\n" +
                banner + "\n" +
                "Hello ♪ I'm " + name + "~\n" +
                "What master piece shall we play?\n" +
                "____________________________________________________________\n";
    }

    public static String printFarewell() {
        return  "Farewell, come play with me again :D\n" +
                "____________________________________________________________\n";
    }

    public static void printTaskDetails(Task task, int taskCounter) {
        System.out.println("    Task has been added successfully ♪");
        System.out.println("        " + task.toString());
        System.out.println("    There are " + taskCounter + " task(s) awaiting your attention~");
    }

    public static void printDeleteTaskDetails(Task task, int taskCounter) {
        System.out.println("    Task has been removed ♪ goodbye task~");
        System.out.println("        " + task.toString());
        System.out.println("    There are " + taskCounter + " task(s) awaiting your attention~");
    }
}
