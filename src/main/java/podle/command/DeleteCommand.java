package podle.command;

import podle.exception.InvalidInputException;
import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

/**
 * Represents a command to permanently delete a task. Upon execution, removes the specified task from memory and storage.
 */
public class DeleteCommand extends Command {

    private String arguments;

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command by removing the target task from the active list and the local file.
     *
     * @param tasks The active task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws Exception If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        int inputNumber = Integer.parseInt(arguments);
        try {
            tasks.deleteTask(inputNumber);
            storage.deleteLine(inputNumber);
        } catch (InvalidInputException e) {
            ui.printError(e.getMessage());
        }
    }
}