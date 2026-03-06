package podle.command;

import podle.task.TaskList;
import podle.ui.Ui;
import podle.storage.Storage;

/**
 * Represents an abstract base class for all executable commands. Provides the blueprint for command execution and exit status.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Executes the core logic associated with the specific command.
     *
     * @param tasks The active task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws Exception If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Checks if this command is intended to terminate the application.
     *
     * @return True if it is an exit command, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}