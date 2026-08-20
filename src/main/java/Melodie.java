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

        while (true) {
            String input = scanner.nextLine();
            System.out.println("    ____________________________________________________________");

            if (input.equals("bye")) {
                break;
            }

            System.out.println("    " + input);
            System.out.println("    ____________________________________________________________");
        }

        System.out.println(printFarewell());
        scanner.close();
    }

    public static String printIntro() {
        String returnString =
                "____________________________________________________________\n" +
                banner + "\n" +
                "Hello ♪ I'm " + name + "~\n" +
                "What master piece shall we play?\n" +
                "____________________________________________________________\n";
        return returnString;
    }

    public static String printFarewell() {
        String returnString =
                "Farewell, come play with me again :D\n" +
                "____________________________________________________________\n";
        return returnString;
    }
}
