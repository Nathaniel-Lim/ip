package melodie.storage;

import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import melodie.task.Deadline;
import melodie.task.Event;
import melodie.task.Task;
import melodie.task.TaskList;
import melodie.task.Todo;

/**
 * Loads and saves Melodie's tasks using a text file.
 *
 * <p>Each task is stored on one line in one of the following formats:
 * <pre>
 * T | 1 | read book
 * D | 0 | return book | 2019-12-02T18:00
 * E | 0 | project meeting | 2019-12-02T14:00 | 2019-12-02T16:00
 * </pre>
 */
public class Storage { // helps read and write data to Melodie.txt
    private final Path filePath;

    /**
     * Creates storage that uses Melodie's default data-file path.
     */
    public Storage() {
        this(Path.of("data", "Melodie.txt"));
    }

    /**
     * Creates storage that uses the specified data-file path.
     *
     * @param filePath Path of the file used to load and save tasks.
     */
    Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads all tasks from the data file.
     * Returns an empty list if the data file does not exist.
     *
     * @return Tasks loaded from the data file.
     * @throws IOException If the data file cannot be read.
     */
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

    /**
     * Writes all tasks to the data file, creating its parent directory when needed.
     *
     * @param tasks Tasks to save.
     * @throws IOException If the data file cannot be written.
     */
    public void write(TaskList tasks) throws IOException {
        Files.createDirectories(filePath.getParent());

        ArrayList<String> lines = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            lines.add(task.toStorageString());
        }

        Files.write(filePath, lines);
    }
}
