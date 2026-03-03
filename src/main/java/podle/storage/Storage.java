package podle.storage;
import podle.task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.*;
import static java.nio.file.Files.readAllLines;

public class Storage {

    private static Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public static void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath.toFile(), true); // create a FileWriter in append mode
        fw.write(task.toFileFormat());
        fw.close();
    }

    public static void markStorageList(int taskIndex, boolean shouldMark)throws IOException{
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
        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                    continue;
            }

            String[] parts = splitLine(line);
            String cateogery = removeWhiteSpaces(parts[1]);
            boolean shouldMark = shouldMark(parts[1]);

            Task newTask = null;

            switch (cateogery) {
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
                    boolean shouldPrint = false;
                    if (shouldMark) {
                        newTask.markDone();
                    }
                    TaskList.addTask(newTask, shouldPrint);
                }
            }
        }

    public static String removeWhiteSpaces(String s){
        return s.replaceAll("\\s+", "");
    }

    public static String[] splitLine(String line)
    {
        return line.split("[|]");
    }

    public static boolean shouldMark(String status){
        String temp = removeWhiteSpaces(status);
        return temp.equals("1");
    }


    public void initializeStorage() throws IOException {
        if (doesFileExist()){
            readFromFile();
        }
        else{
            createNewFile();
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
