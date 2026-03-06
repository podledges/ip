package podle.command;

import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

/**
 * Represents a command that the parser could not recognize.
 * Handles graceful error reporting to the user.
 */
public class UnknownCommand extends Command {

    /**
     * Executes the unknown command by printing a standardized error message.
     *
     * @param tasks The active task list (unused in this command).
     * @param ui The user interface used to display the error message.
     * @param storage The storage handler (unused in this command).
     * @throws InterruptedException If the execution is interrupted.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InterruptedException {
        ui.printUnknownCommand();
    }
}