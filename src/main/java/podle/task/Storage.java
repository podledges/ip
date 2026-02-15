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


    public static void appendToFile(String textToAppend, Category c) throws IOException {
        FileWriter fw = new FileWriter(filePath.toFile(), true); // create a FileWriter in append mode
        if(c == TODO){
            fw.write(String.format("T | 0 | " + textToAppend));
        }
        fw.close();
    }
    public static void appendToFile(String[] textToAppend, Category c) throws IOException {
        FileWriter fw = new FileWriter(filePath.toFile(), true); // create a FileWriter in append mode
        if (c == DEADLINE){
            fw.write(String.format("D | 0 | " + textToAppend[0] + "|" + textToAppend[1]));
        }
        else if(c == EVENT){
            fw.write(String.format("E | 0 | " + textToAppend[0] + "|" + textToAppend[1] + "/" + textToAppend[2]));
        }
        fw.close();
    }

    public static void updateMark(int taskIndex)throws IOException{
        List<String> lines = readAllLines(filePath);
        int lineIndex = taskIndex -1;
        if(lineIndex >= 0 && lineIndex < lines.size()){
            String line = lines.get(lineIndex);
            String[] splitLine = line.split("[|]");
            splitLine[1] = " 1 ";
            lines.set(lineIndex, String.join("|", splitLine));
        }
        Files.write(filePath,lines);
    }

    public static void updateUnMark(int taskIndex)throws IOException{
        List<String> lines = readAllLines(filePath);
        int lineIndex = taskIndex -1;
        if(lineIndex >= 0 && lineIndex < lines.size()){
            String line = lines.get(lineIndex);
            String[] splitLine = line.split("[|]");
            splitLine[1] = " 0 ";
            lines.set(lineIndex, String.join("|", splitLine));
        }
        Files.write(filePath,lines);
    }


    private static void readFromFile() throws IOException{
        List<String> lines = readAllLines(filePath);
        int index = 0;
        String[] toMark = new String[100];  //TODO fix this stupid method
        int markIndex = 0;
        for(String line: lines){
            String[] splitLine = line.split("[|]");
            String cateogeryLine = splitLine[0].replaceAll("\\s+", "");
            String statusLine = splitLine[1].replaceAll("\\s+", "");

            if (cateogeryLine.equals("T")){
                TaskList.addTask(new ToDo(splitLine[2]));
                if(statusLine.equals("1")){
                    toMark[markIndex] = String.valueOf(index);
                    markIndex++;
                }
            }
            else if (cateogeryLine.equals("D")){
                String[] deadlineString = new String[2];
                deadlineString[0] = splitLine[2];
                deadlineString[1] = splitLine[3];
                TaskList.addTask(new Deadlines(deadlineString));
                if(statusLine.equals("1")){
                    toMark[markIndex] = String.valueOf(index);
                    markIndex++;
                }
            }
            else if (cateogeryLine.equals("E")){
                String[] eventString = new String[3];
                String[] tempString = splitLine[3].split("[-/]");
                eventString[0] = splitLine[2];
                eventString[1] = tempString[0];
                eventString[1] = tempString[1];
                TaskList.addTask(new Events(eventString));
                if(statusLine.equals("1")){
                    toMark[markIndex] = String.valueOf(index);
                    markIndex++;
                }
            }
            index++;
        }
        TaskList.markList(toMark, true);
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
