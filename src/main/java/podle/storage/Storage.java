package podle.storage;
import podle.task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.*;
import static java.nio.file.Files.readAllLines;

/**
 * Handles all read and write operations to the local storage file.
 * Ensures that the user's task list persists across application restarts.
 */
public class Storage {

    private final Path filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path where the task list data is stored.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Appends a newly created task to the end of the storage file.
     *
     * @param task The task to be saved to the file.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath.toFile(), true); // create a FileWriter in append mode
        fw.write(task.toFileFormat());
        fw.close();
    }

    /**
     * Updates the completion status of a specific task directly within the storage file.
     * Reads all lines, modifies the specified line, and rewrites the file.
     *
     * @param taskIndex The 1-based index of the task to update.
     * @param shouldMark True to mark the task as done (1), false to mark as not done (0).
     * @throws IOException If an error occurs while reading or writing to the file.
     */
    public void markStorageList(int taskIndex, boolean shouldMark) throws IOException {
        List<String> lines = readAllLines(filePath);
        int lineIndex = taskIndex -1;
        if (lineIndex >= 0 && lineIndex < lines.size()) {
            String line = lines.get(lineIndex);
            String[] splitLine = line.split("[|]");
            if (shouldMark) {
                splitLine[1] = " 1 ";
                lines.set(lineIndex, String.join("|", splitLine));
            }
            else {
                splitLine[1] = " 0 ";
                lines.set(lineIndex, String.join("|", splitLine));
            }
        }
        Files.write(filePath,lines);
    }

    /**
     * Deletes a specific task from the storage file by removing its corresponding line.
     *
     * @param taskIndex The 1-based index of the task to remove.
     * @throws IOException If an error occurs while reading or writing to the file.
     */
    public void deleteLine(int taskIndex) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        int internalIndex = taskIndex - 1;
        if (internalIndex >= 0 && internalIndex < lines.size()) {
            lines.remove(internalIndex);
            Files.write(filePath, lines);
        }
        else {
            System.out.println("Invalid index! No line deleted from file.");
        }
    }

    /**
     * Reads tasks from the storage file and populates the active TaskList.
     * Decodes each line into the appropriate Task subclass (ToDo, Deadline, Event).
     *
     * @param tasks The TaskList to populate with saved data.
     * @throws IOException If an error occurs while reading the file.
     */
    public void readFromFile(TaskList tasks) throws IOException {
        List<String> lines = readAllLines(filePath);
        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            String[] parts = splitLine(line);
            String category = removeWhiteSpaces(parts[0]);
            boolean shouldMark = shouldMark(parts[1]);

            Task newTask = null;

            switch (category) {
                case "T":
                    newTask = new ToDo(parts[2].trim());
                    break;
                case "D":
                    newTask = Deadline.fromFileFormat(parts);
                    break;
                case "E":
                    newTask = Event.fromFileFormat(parts);
                    break;
                default:
                    System.out.println("Unknown and unfriendly category detected ERROR" + category);
                    break;
            }
            if (newTask != null) {
                boolean shouldPrint = false;
                if (shouldMark) {
                    newTask.markDone(shouldPrint);
                }
                tasks.addTask(newTask, shouldPrint);
            }
        }
    }

    /**
     * Removes all whitespace characters from a given string.
     * Used primarily to sanitize category codes and status flags from the save file.
     *
     * @param s The string to process.
     * @return The string with all whitespaces removed.
     */
    public String removeWhiteSpaces(String s){
        return s.replaceAll("\\s+", "");
    }

    /**
     * Splits a stored line of text into components based on the standard pipe (|) delimiter.
     *
     * @param line The raw line from the storage file.
     * @return An array of strings split by the delimiter.
     */
    public String[] splitLine(String line) {
        return line.split("[|]");
    }

    /**
     * Determines if a stored task status indicates it is completed.
     *
     * @param status The status string from the file (e.g., "1" or "0").
     * @return True if the task is marked as done, false otherwise.
     */
    public boolean shouldMark(String status) {
        String temp = removeWhiteSpaces(status);
        return temp.equals("1");
    }

    /**
     * Initializes the storage setup by checking if the save file exists.
     * If it exists, it loads the existing tasks; otherwise, it creates a new file.
     *
     * @param tasks The TaskList to populate if a save file is found.
     * @throws IOException If an error occurs during directory/file creation or reading.
     */
    public void initializeStorage(TaskList tasks) throws IOException {
        if (doesFileExist()) {
            readFromFile(tasks);
        }
        else {
            createNewFile();
        }
    }

    /**
     * Checks whether the target storage file already exists on the disk.
     *
     * @return True if the file exists, false otherwise.
     */
    public boolean doesFileExist() {
       return exists(filePath);
    }

    /**
     * Creates a new storage file and any necessary parent directories.
     * Used when the application runs for the first time or if the file was deleted.
     */
    public void createNewFile() {
        try {
            Path parentDir = filePath.getParent();
            if (parentDir != null && Files.notExists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
