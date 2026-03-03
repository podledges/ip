package podle;

import podle.storage.Storage;
import podle.task.TaskList;

import podle.ui.*;

import java.io.IOException;
import java.nio.file.Path;

public class PodleGPT {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public PodleGPT(){
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(Path.of("./data/podle.txt"));
    }

    public void boot() throws InterruptedException, IOException {
        ui.printGreeting();
        storage.initializeStorage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getInput();
             //   ExecuteCommand command = Parser.parse(input);
            //    command.execute(tasks, ui);
           //     isExit = command.isExit();
            } catch (Exception e) {
                ui.printError(e.getMessage());
            }
        }
        exit();
    }


    private void exit(){
        System.exit(0);
    }
    public static void main(String[] args) throws InterruptedException, IOException {
        PodleGPT podleGPT = new PodleGPT();
        podleGPT.boot();
    }
}
