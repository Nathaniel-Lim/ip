package melodie.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import melodie.task.Deadline;
import melodie.task.Event;
import melodie.task.Task;
import melodie.task.TaskList;
import melodie.task.Todo;

/**
 *  T | 1 | read book
 *  D | 0 | return book | June 6th
 *  E | 0 | project meeting | Mon 2pm | 4pm
 */
public class Storage {
    private final Path filePath;

    public Storage() {
        this(Path.of("data", "Melodie.txt"));
    }

    Storage(Path filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> read() throws IOException {
        if (!Files.exists(this.filePath)) {
            return new ArrayList<>();
        }

        Scanner scanner = new Scanner(this.filePath);
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

            if (parts[1].equals("1")) { // 1 represents complete; 0 represents incomplete.
                task.mark();
            }
            tasks.add(task);
        }
        scanner.close();
        return tasks;
    }

    public void write(TaskList tasks) throws IOException {
        Files.createDirectories(this.filePath.getParent());

        ArrayList<String> lines = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            lines.add(task.toStorageString());
        }

        Files.write(this.filePath, lines);
    }
}
