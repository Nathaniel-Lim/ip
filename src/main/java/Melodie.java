import java.util.Scanner;

public class Melodie {
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
        Task[] tasks = new Task[100];
        int taskCounter = 0;

        while (true) {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String taskDescription = parts.length > 1 ? parts[1] : "";

            System.out.println("    ____________________________________________________________");

            if (input.equals("bye")) {
                break;
            }

            try {
                switch (command) {
                    case "mark":
                    case "unmark":
                        int taskNum = Integer.parseInt(taskDescription) - 1;
                        if (taskNum < 0 || taskNum >= taskCounter) {
                            throw new MelodieException("Please enter a valid task number :(");
                        }

                        if (command.equals("mark")) {
                            tasks[taskNum].mark();
                            System.out.println("    Good job! Task has been marked as done~");
                        } else {
                            tasks[taskNum].unmark();
                            System.out.println("    Task has been marked as incomplete, good luck ♫");
                        }

                        System.out.println("    " + tasks[taskNum].toString());
                        break;

                    case "todo":
                        if (taskDescription.isBlank()) {
                            throw new MelodieException("You can't leave the description of a todo empty :(");
                        }
                        Task todo = new Todo(taskDescription);
                        tasks[taskCounter++] = todo;
                        printTaskDetails(todo, taskCounter);
                        break;

                    case "deadline":
                        String[] deadlineParts = taskDescription.split("/by ", 2);
                        if (deadlineParts.length != 2
                                || deadlineParts[0].isBlank()
                                || deadlineParts[1].isBlank()) {
                            throw new MelodieException("Please enter a valid task description and due date :(\n"
                                                     + "Format: deadline <task description> /by <due date>");
                        }

                        String description = deadlineParts[0].trim();
                        String dueDate = deadlineParts[1].trim();
                        Task deadline = new Deadline(description, dueDate);
                        tasks[taskCounter++] = deadline;
                        printTaskDetails(deadline, taskCounter);
                        break;

                    case "event":
                        String[] fromParts = taskDescription.split("/from ", 2);
                        if (fromParts.length != 2
                                || fromParts[0].isBlank()) {
                            throw new MelodieException("Please enter a valid task description, start, and end :(\n"
                                                     + "Format: event <description> /from <start> /to <end>");
                        }

                        String[] toParts = fromParts[1].split("/to ", 2);
                        if (toParts.length != 2
                                || toParts[0].isBlank()
                                || toParts[1].isBlank()) {
                            throw new MelodieException("Please enter a valid task description :(\n"
                                                     + "Format: event <description> /from <start> /to <end>");
                        }

                        String descriptions = fromParts[0].trim(); // there has to be a more scalable way
                        String start = toParts[0].trim();
                        String end = toParts[1].trim();
                        Task event = new Event(descriptions, start, end);
                        tasks[taskCounter++] = event;
                        printTaskDetails(event, taskCounter);
                        break;

                    case "list":
                        if (taskCounter == 0) {
                            System.out.println("    Your list is currently empty; let's get started shall we? ♪");
                            break;
                        }
                        System.out.println("    Here are the tasks in your list ♪");
                        for (int i = 0; i < taskCounter; i++) {
                            System.out.println("    " + (i + 1) + ". " + tasks[i].toString());
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
            }
            System.out.println("    ____________________________________________________________");
        }

        System.out.println(printFarewell());
        scanner.close();
    }

    public static String printIntro() {
//        String returnString =
        return "____________________________________________________________\n" +
                banner + "\n" +
                "Hello ♪ I'm " + name + "~\n" +
                "What master piece shall we play?\n" +
                "____________________________________________________________\n";
//        return returnString;
    }

    public static String printFarewell() {
        return  "Farewell, come play with me again :D\n" +
                "____________________________________________________________\n";
//        return returnString;
    }

    public static void printTaskDetails(Task task, int taskCounter) {
        System.out.println("    Task has been added successfully ♪");
        System.out.println("        " + task.toString());
        System.out.println("    There are " + taskCounter + " task(s) awaiting your attention~");
    }
}
