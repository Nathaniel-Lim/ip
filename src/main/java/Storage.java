import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *  T | 1 | read book
 *  D | 0 | return book | June 6th
 *  E | 0 | project meeting | Mon 2pm | 4pm
 */
public class Storage { // helps read and write data to Melodie.txt
    private final Path filePath = Path.of("data", "Melodie.txt");

    public ArrayList<Task> read() throws IOException {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        Scanner scanner = new Scanner(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isBlank()) {
                continue;
            }
            String[] parts = line.split(" \\| ");
            Task task;

            switch (parts[0]) {
                case "T":
                    task = new Todo(parts[2]);
                    break;

                case "D":
                    task = new Deadline(parts[2], LocalDateTime.parse(parts[3]));
                    break;

                case "E":
                    task = new Event(
                            parts[2],
                            LocalDateTime.parse(parts[3]),
                            LocalDateTime.parse(parts[4]));
                    break;

                default:
                    continue;
            }

            if (parts[1].equals("1")) { // 1 for complete, 0 for incomplete
                task.mark();
            }
            tasks.add(task);
        }
        scanner.close();
        return tasks;
    }

    public void write(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(filePath.getParent());

        ArrayList<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            lines.add(task.toStorageString());
        }

        Files.write(filePath, lines);
    }
}
