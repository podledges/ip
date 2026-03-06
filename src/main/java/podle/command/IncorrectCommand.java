package podle.command;

import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command {

    public final String errorMessage;

    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the command by printing a standardized error message to the user.
     *
     * @param tasks The active task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws InterruptedException If the execution is interrupted.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printError(errorMessage);
    }

}