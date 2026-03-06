package podle;

import podle.command.Command;
import podle.parser.Parser;
import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.*;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Represents the main entry point of the Podle application. Initializes core components and manages the main execution loop.
 */
public class PodleGPT {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a PodleGPT instance. Initializes the user interface, task list, and storage components.
     *
     * @param filepath The path where the task data will be stored and loaded from.
     */
    public PodleGPT(Path filepath) {
        this.ui = new Ui();
        this.tasks = new TaskList(this.ui);
        this.storage = new Storage(filepath);
    }

    /**
     * Starts the main application loop. Continuously reads user input, parses commands, and executes them until an exit command is given.
     *
     * @throws InterruptedException If the application is interrupted during artificial delays.
     * @throws IOException If an error occurs while reading from or writing to the storage file.
     */
    public void boot() throws InterruptedException, IOException {
        ui.printGreeting();
        storage.initializeStorage(tasks);

        boolean isRunning = true;

        while (isRunning) {
            try {
                String inputCmd = ui.getInput();
                Command cmd = Parser.parse(inputCmd);
                cmd.execute(tasks, ui, storage);
                if(cmd.isExit()) {
                    isRunning = false;
                }
            } catch (Exception e) {
                ui.printError(e.getMessage());
            }
        }
        exit();
    }

    /**
     * Terminates the application safely. Forces the Java Virtual Machine to shut down.
     */
    private void exit(){
        System.exit(0);
    }

    /**
     * Serves as the application runner. Creates a new PodleGPT instance and boots it up.
     *
     * @param args Command line arguments (not utilized).
     * @throws InterruptedException If the startup sequence is interrupted.
     * @throws IOException If the storage file cannot be accessed during startup.
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        PodleGPT podleGPT = new PodleGPT(Path.of("./data/podle.txt"));
        podleGPT.boot();
    }
}