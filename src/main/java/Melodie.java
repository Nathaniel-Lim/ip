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

        Task[] tasks = new Task[100];
        int taskCounter = 0;

        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            System.out.println("    ____________________________________________________________");

            if (input.equals("bye")) {
                break;
            }

            if (parts[0].equals("mark")) {
                int taskNum = Integer.parseInt(parts[1]) - 1;
                tasks[taskNum].mark();
                System.out.println("    Good job! Task has been marked as done~");
                System.out.println("    " + tasks[taskNum].toString());
            } else if (parts[0].equals("unmark")) {
                int taskNum = Integer.parseInt(parts[1]) - 1;
                tasks[taskNum].unmark();
                System.out.println("    Task has been marked as incomplete, good luck ♫");
                System.out.println("    " + tasks[taskNum].toString());
            } else if (input.equals("list")) {
                System.out.println("    Here are the tasks in your list ♪");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks[i].toString());
                }
            } else {
                tasks[taskCounter++] = new Task(input);
                System.out.println("    added: " + input);
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
}
