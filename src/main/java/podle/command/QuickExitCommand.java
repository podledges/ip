package podle.command;

import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

/**
 * Represents a command to quickly terminate the application. Upon execution, flags the application to exit immediately.
 */
public class QuickExitCommand extends Command {

    public QuickExitCommand() {
        this.isExit = true;
    }

    /**
     * Executes the quick exit sequence by printing a brief farewell message and setting the exit flag.
     *
     * @param tasks The active task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeBye();
        this.isExit = true;
    }
}