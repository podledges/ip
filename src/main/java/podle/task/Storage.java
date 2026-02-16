package podle.task;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.*;
import static java.nio.file.Files.readAllLines;
import static podle.task.Category.*;

public class Storage {


    private static Path filePath = Paths.get("./data/PodleGPT.txt"); // .txt file location


    public static void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath.toFile(), true); // create a FileWriter in append mode
        fw.write(task.toFileFormat() + System.lineSeparator());
        fw.close();
    }

    public static void updateMark(int taskIndex, boolean shouldMark)throws IOException{
        List<String> lines = readAllLines(filePath);
        int lineIndex = taskIndex -1;
        if(lineIndex >= 0 && lineIndex < lines.size()){
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



    public static void deleteLine(int taskIndex) throws IOException{
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

    public static void readFromFile() throws IOException {
        List<String> lines = readAllLines(filePath);
        int lineIndex = 0;
        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split("[|]");

            String cateogeryLine = parts[0].replaceAll("\\s+", "");
            String statusLine = parts[1].replaceAll("\\s+", "");

            Task newTask = null;

            // This switch acts as your "Factory"
            switch (cateogeryLine) {
            case "T":
                newTask = new ToDo(parts[2].trim());
                break;
            case "D":
                newTask = Deadlines.fromFileFormat(parts);
                break;
            case "E":
                newTask = Events.fromFileFormat(parts);
                break;
            }
            if (newTask != null) {
                if (statusLine.equals("1")) newTask.markDone(); // Use that concrete method we talked about!
                TaskList.addTask(newTask);
            }
        }
    }

    public static boolean doesFileExist(){
       return exists(filePath);
    }

    public static void createNewFile(){
        try{
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
