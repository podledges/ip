package podle.command;

import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

/**
 * Represents a command to safely exit the application. Upon execution, displays a farewell message and terminates the program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Executes the exit sequence by printing a goodbye message and setting the exit flag.
     *
     * @param tasks The active task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws InterruptedException If the execution is interrupted.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InterruptedException {
        ui.printBye();
        this.isExit = true;
    }
}