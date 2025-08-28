import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DEFAULT_FILE_PATH = "./data/phuc.txt";
    private static final String DATA_DIRECTORY = "./data/";

    private final String filePath;

    public Storage() {
        this.filePath = DEFAULT_FILE_PATH;
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        java.io.File file = new java.io.File(filePath);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.writeToFile());
                writer.write("\n");
            }
        }
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                boolean isDone = parts[1].equals("true");

                switch (parts[0]) {
                case "E":
                    tasks.add(new EventTask(parts[2], parts[3], parts[4], isDone));
                    break;
                case "D":
                    tasks.add(new DeadlineTask(parts[2], parts[3], isDone));
                    break;
                case "T":
                    tasks.add(new ToDoTask(parts[2], isDone));
                    break;
                default:
                    throw new IOException("File format error");
                }
            }
        }

        return tasks;
    }
}
