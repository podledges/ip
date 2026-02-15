package podle;

import podle.command.CommandParser;
import podle.command.ExecuteCommand;

import podle.task.Storage;
import podle.task.TaskList;

import podle.ui.*;

public class PodleGPT {

    public static void main(String[] args) throws InterruptedException {

        TaskList tasks = new TaskList();
        Ui userinterface = new Ui();
        userinterface.printGreeting();
        boolean isexit = false;
        if (!Storage.doesFileExist()){
            Storage.createNewFile();
        }
        while (!isexit) {
            try {
                String input = userinterface.getInput();
                ExecuteCommand execute = CommandParser.Parse(input);
                execute.Execute(tasks, userinterface);
            } catch (Exception e) {
                userinterface.printError("failed to execute Command");
            }
        }
    }
}
