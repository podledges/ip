package podle;

import podle.command.Command;
import podle.parser.Parser;
import podle.storage.Storage;
import podle.task.TaskList;

import podle.ui.*;

import java.io.IOException;
import java.nio.file.Path;

public class PodleGPT {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public PodleGPT(Path filepath){
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(filepath);
    }

    public void boot() throws InterruptedException, IOException {
        ui.printGreeting();
        storage.initializeStorage(tasks);
        while (!Command.isExit()) {
            try {
                String inputCmd = ui.getInput();
                ui.printLine();
                Command cmd = Parser.parse(inputCmd);
                cmd.execute(tasks, ui, storage);
            } catch (Exception e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        exit();
    }

    private void exit(){
        System.exit(0);
    }
    public static void main(String[] args) throws InterruptedException, IOException {
        PodleGPT podleGPT = new PodleGPT(Path.of("./data/podle.txt"));
        podleGPT.boot();
    }
}
